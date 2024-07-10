package com.mockproject.AuctionManagement.dto.response;

import com.mockproject.AuctionManagement.entity.RoleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private String message = "Login successful";
    private Set<RoleEntity> roles;
    private boolean authenticated;
    private String username;
}