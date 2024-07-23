package com.mockproject.AuctionManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message = "Login successful";
    private Long idUser;
    private List<String> roles;
    private boolean authenticated;
    private String username;
}