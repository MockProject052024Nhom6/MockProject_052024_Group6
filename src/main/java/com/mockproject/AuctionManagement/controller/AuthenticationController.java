package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.request.AuthenticationRequest;
import com.mockproject.AuctionManagement.dto.request.IntrospectRequest;
import com.mockproject.AuctionManagement.dto.request.LogoutRequest;
import com.mockproject.AuctionManagement.dto.request.RegisterRequestDTO;
import com.mockproject.AuctionManagement.dto.response.*;
import com.mockproject.AuctionManagement.service.AuthenticationService;
import com.mockproject.AuctionManagement.service.TokenService;
import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {
    TokenService tokenService;
  
    AuthenticationService authenticationService;

    @Operation(
            summary = "login ",
            description = "provide email and password for user to access the system"
    )
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
  
    @Operation(
            summary = "register",
            description = "register new user"
    )
    @PostMapping("/register")
    public ResponseData<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "Register successful", authenticationService.register(request));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}