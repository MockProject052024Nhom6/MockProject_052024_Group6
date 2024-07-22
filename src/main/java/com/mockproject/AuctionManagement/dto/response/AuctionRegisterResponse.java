package com.mockproject.AuctionManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mockproject.AuctionManagement.entity.AuctionEntity;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.enums.UserHasAuctionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionRegisterResponse {
    Integer id;
    Double deposits;
    Date depositsDate;
    UserHasAuctionStatus userHasAuctionStatus;
    Integer status;
    AuctionResponse auctionEntity;
    @JsonIgnoreProperties({"createBy", "modifiesBy", "createdDate", "modifiedDate", "userHasRoleEntities"})
    UserResponse userEntity;
}
