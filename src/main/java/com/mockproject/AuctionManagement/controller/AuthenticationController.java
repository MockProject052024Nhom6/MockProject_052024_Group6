package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.request.AuthenticationRequest;
import com.mockproject.AuctionManagement.dto.request.IntrospectRequest;
import com.mockproject.AuctionManagement.dto.request.LogoutRequest;
import com.mockproject.AuctionManagement.dto.response.ApiResponse;
import com.mockproject.AuctionManagement.dto.response.AuthenticationResponse;
import com.mockproject.AuctionManagement.dto.response.IntrospectResponse;
import java.text.ParseException;
import com.mockproject.AuctionManagement.service.AuthenticationService;
import com.mockproject.AuctionManagement.service.TokenService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    TokenService tokenService;
    AuthenticationService authenticationService;
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }
    @PostMapping("/token")
    Long responseToken(@RequestBody String token) throws ParseException, JOSEException {
        return tokenService.getUserIdFromToken(token);
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}
