package com.mockproject.AuctionManagement.dto.request;

import com.mockproject.AuctionManagement.entity.AuctionEntity;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.enums.UserHasAuctionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionRegisterRequest {
    Double deposits;
    Date depositsDate;
    UserHasAuctionStatus userHasAuctionStatus;
    Integer status;
    Long idAuction;
    Long idUser;
}
