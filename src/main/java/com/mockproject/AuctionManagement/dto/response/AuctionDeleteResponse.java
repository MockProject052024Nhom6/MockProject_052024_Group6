package com.mockproject.AuctionManagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionDeleteResponse {
    Long idAuction;
    Integer status = 0;
    String message = "success";
}
