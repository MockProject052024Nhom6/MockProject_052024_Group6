package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.request.AuthenticationRequest;
import com.mockproject.AuctionManagement.dto.request.RegisterRequestDTO;
import com.mockproject.AuctionManagement.dto.response.ApiResponse;
import com.mockproject.AuctionManagement.dto.response.AuthenticationResponse;
import com.mockproject.AuctionManagement.dto.response.ResponseData;
import com.mockproject.AuctionManagement.dto.response.ResponseError;
import com.mockproject.AuctionManagement.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

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