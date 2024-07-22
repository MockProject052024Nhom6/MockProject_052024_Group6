package com.mockproject.AuctionManagement.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class TokenService {
    private static final String SIGNER_KEY = "your-signer-key-here";

    public Long getUserIdFromToken(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        if (!signedJWT.verify(verifier)) {
            throw new JOSEException("Token signature is invalid");
        }

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expirationTime.before(new Date())) {
            throw new JOSEException("Token has expired");
        }
        String userIdString = signedJWT.getJWTClaimsSet().getSubject();

        if (userIdString == null) {
            throw new JOSEException("Token does not contain userId");
        }

        return Long.parseLong(userIdString);
    }
}
