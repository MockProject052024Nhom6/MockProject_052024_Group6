package com.mockproject.AuctionManagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String username;
}
