package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.configuration.CustomJwtDecoder;
import com.mockproject.AuctionManagement.dto.request.AuctionRegisterRequest;
import com.mockproject.AuctionManagement.dto.request.AuctionRequestCreate;
import com.mockproject.AuctionManagement.dto.request.AuctionRequestUpdate;
import com.mockproject.AuctionManagement.dto.request.IntrospectRequest;
import com.mockproject.AuctionManagement.dto.response.AuctionDeleteResponse;
import com.mockproject.AuctionManagement.dto.response.AuctionRegisterResponse;
import com.mockproject.AuctionManagement.dto.response.AuctionResponse;
import com.mockproject.AuctionManagement.entity.AuctionEntity;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.entity.UserHasAuctionEntity;
import com.mockproject.AuctionManagement.exception.AppException;
import com.mockproject.AuctionManagement.exception.ErrorCode;
import com.mockproject.AuctionManagement.mapper.AuctionMapper;
import com.mockproject.AuctionManagement.mapper.AuctionRegisterMapper;
import com.mockproject.AuctionManagement.repository.AuctionRegisterRepository;
import com.mockproject.AuctionManagement.repository.AuctionRepository;
import com.mockproject.AuctionManagement.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.oauth2.jwt.Jwt;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuctionService {
    AuctionRepository auctionRepository;
    AuctionMapper auctionMapper;
    AuthenticationService authenticationService;
    UserRepository userRepository;
    AuctionRegisterMapper auctionRegisterMapper;
    AuctionRegisterRepository auctionRegisterRepository;


    @Transactional
    public AuctionResponse AuctionCreate(AuctionRequestCreate request, IntrospectRequest token) throws ParseException, JOSEException {
        Long userId = authenticationService.introspect(token).getUserId();

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        AuctionEntity auction = auctionMapper.toAuction(request);
        auction.setUserEntity(userEntity);

        AuctionEntity savedAuction = auctionRepository.save(auction);

        return auctionMapper.toAuctionResponse(savedAuction);
    }

    @Transactional
    public AuctionResponse AuctionUpdate(Long idAuction, AuctionRequestUpdate request, IntrospectRequest token) throws ParseException, JOSEException {
        Long idUser = authenticationService.introspect(token).getUserId();
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        AuctionEntity auction = auctionRepository.findByIdAuctionAndUserEntity_IdUser(idAuction, idUser)
                .orElseThrow(() -> new AppException(ErrorCode.AUCTION_NOT_FOUND));

        auctionMapper.updateAuctionFromDto(request, auction);

        AuctionEntity updatedAuction = auctionRepository.save(auction);

        return auctionMapper.toAuctionResponse(updatedAuction);
    }

    @Transactional
    public AuctionDeleteResponse AuctionDelete(Long idAuction, IntrospectRequest token) throws ParseException, JOSEException {
        Long idUser = authenticationService.introspect(token).getUserId();
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        AuctionEntity auction = auctionRepository.findByIdAuctionAndUserEntity_IdUser(idAuction,idUser)
                .orElseThrow(() -> new AppException(ErrorCode.AUCTION_NOT_FOUND));

        auctionRepository.delete(auction);
        AuctionDeleteResponse response = new AuctionDeleteResponse();
        response.setIdAuction(idAuction);
        response.setStatus(0);
        response.setMessage("Auction deleted successfully");

        return response;
    }

    public Page<AuctionResponse> getAllAuctions(Pageable pageable) {
        Page<AuctionEntity> auctions = auctionRepository.findAll(pageable);
        return auctions.map(auctionMapper::toAuctionResponse);
    }

    public AuctionRegisterResponse AuctionRegister(AuctionRegisterRequest request, Long idAuction, IntrospectRequest token) throws ParseException, JOSEException {
        Long idUser = authenticationService.introspect(token).getUserId();
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        AuctionEntity auction = auctionRepository.findByIdAuctionAndUserEntity_IdUser(idAuction, idUser)
                .orElseThrow(() -> new AppException(ErrorCode.AUCTION_NOT_FOUND));

        UserHasAuctionEntity userHasAuctionEntity = auctionRegisterMapper.toRegisterAuction(request);
        userHasAuctionEntity.setAuctionEntity(auction);
        userHasAuctionEntity.setUserEntity(userEntity);

        UserHasAuctionEntity saveUserHasAuctionEntity = auctionRegisterRepository.save(userHasAuctionEntity);
        return auctionRegisterMapper.toResponse(saveUserHasAuctionEntity);

    }

}
