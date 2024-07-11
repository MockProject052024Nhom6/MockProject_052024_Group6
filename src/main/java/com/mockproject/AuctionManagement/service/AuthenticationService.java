package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.request.AuthenticationRequest;
import com.mockproject.AuctionManagement.dto.request.IntrospectRequest;
import com.mockproject.AuctionManagement.dto.request.RefreshRequest;
import com.mockproject.AuctionManagement.dto.request.RegisterRequestDTO;
import com.mockproject.AuctionManagement.dto.response.AuthenticationResponse;
import com.mockproject.AuctionManagement.dto.response.IntrospectResponse;
import com.mockproject.AuctionManagement.entity.InvalidatedToken;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.entity.RoleEntity;
import com.mockproject.AuctionManagement.entity.UserHasRoleEntity;
import com.mockproject.AuctionManagement.enums.UserStatus;
import com.mockproject.AuctionManagement.exception.AppException;
import com.mockproject.AuctionManagement.exception.ErrorCode;
import com.mockproject.AuctionManagement.repository.InvalidatedTokenRepository;
import com.mockproject.AuctionManagement.repository.RoleRepository;
import com.mockproject.AuctionManagement.repository.UserHasRoleRepository;
import com.mockproject.AuctionManagement.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    @PersistenceContext
    private EntityManager entityManager;
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    RoleRepository roleRepository;
    UserHasRoleRepository userHasRoleRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        List<String> roleNames = user.getUserHasRoleEntities().stream()
                .map(userHasRole -> userHasRole.getRoleEntity().getRoleName())
                .collect(Collectors.toList());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .roles(roleNames)
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .message("Login successful")
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    private String generateToken(UserEntity user, long duration) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(duration, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private String generateAccessToken(UserEntity user) {
        return generateToken(user, VALID_DURATION);
    }

    private String generateRefreshToken(UserEntity user) {
        return generateToken(user, REFRESHABLE_DURATION);
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        RoleEntity role = roleRepository.findByRoleName("User").orElseThrow(() -> new RuntimeException("Role not found"));

        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userStatus(UserStatus.ACTIVE)
                .status(1)
                .build();

        UserHasRoleEntity userHasRoleEntity = UserHasRoleEntity.builder()
                .roleEntity(role)
                .userEntity(user)
                .build();

        userRepository.save(user);
        userHasRoleRepository.save(userHasRoleEntity);

        entityManager.flush();
        entityManager.refresh(user);

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        List<String> roleNames = user.getUserHasRoleEntities().stream()
                .map(userHasRole -> userHasRole.getRoleEntity().getRoleName())
                .collect(Collectors.toList());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .roles(roleNames)
                .build();
    }
}
