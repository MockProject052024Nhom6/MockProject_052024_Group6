package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.request.AuctionRegisterRequest;
import com.mockproject.AuctionManagement.dto.request.AuctionRequestCreate;
import com.mockproject.AuctionManagement.dto.request.AuctionRequestUpdate;
import com.mockproject.AuctionManagement.dto.request.IntrospectRequest;
import com.mockproject.AuctionManagement.dto.response.ApiResponse;
import com.mockproject.AuctionManagement.dto.response.AuctionRegisterResponse;
import com.mockproject.AuctionManagement.dto.response.AuctionResponse;
import com.mockproject.AuctionManagement.service.AuctionService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("user/auction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuctionController {
    AuctionService auctionService;
    @PostMapping(value = "/register/{auctionId}", consumes = "application/json", produces = "application/json")
    public ApiResponse<AuctionRegisterResponse> auctionRegister(
            @RequestBody AuctionRegisterRequest request,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @PathVariable Long auctionId) throws ParseException, JOSEException {
        String token = authorizationHeader.substring("Bearer ".length());
        IntrospectRequest introspectRequest = new IntrospectRequest();
        introspectRequest.setToken(token);
        AuctionRegisterResponse result = auctionService.AuctionRegister(request,auctionId,introspectRequest);
        return ApiResponse.<AuctionRegisterResponse>builder().result(result).build();
    }


    @PostMapping()
    public ApiResponse<AuctionResponse> auctionCreate(
            @RequestBody AuctionRequestCreate request,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) throws ParseException, JOSEException {
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }
        String token = authorizationHeader.substring(7);
        IntrospectRequest introspectRequest = new IntrospectRequest();
        introspectRequest.setToken(token);
        AuctionResponse result = auctionService.AuctionCreate(request, introspectRequest);
        return ApiResponse.<AuctionResponse>builder().result(result).build();
    }

    @PutMapping("/{auctionId}")
    public ApiResponse<AuctionResponse> auctionUpdate(@PathVariable Long auctionId, @RequestBody AuctionRequestUpdate request, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) throws ParseException, JOSEException {
        String token = authorizationHeader.substring("Bearer ".length());
        IntrospectRequest introspectRequest = new IntrospectRequest();
        introspectRequest.setToken(token);
        AuctionResponse result = auctionService.AuctionUpdate(auctionId, request, introspectRequest);
        return ApiResponse.<AuctionResponse>builder().result(result).build();
    }

    @GetMapping()
    public ResponseEntity<Page<AuctionResponse>> getAllAuctions(Pageable pageable) {
        Page<AuctionResponse> auctions = auctionService.getAllAuctions(pageable);
        return ResponseEntity.ok(auctions);
    }
}
