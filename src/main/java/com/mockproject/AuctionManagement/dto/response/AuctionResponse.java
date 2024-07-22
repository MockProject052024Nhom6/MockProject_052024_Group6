package com.mockproject.AuctionManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.enums.AuctionStatus;
import com.mockproject.AuctionManagement.enums.TypeAuction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionResponse {
    Long idAuction;
    String auctionName;
    String address;
    Integer numberOfParticipants;
    Date startTime;
    Date endTime;
    TypeAuction typeAuction;
    AuctionStatus auctionStatus;
    Integer status;
    @JsonIgnoreProperties({"createBy", "modifiesBy", "createdDate", "modifiedDate", "userHasRoleEntities"})
    UserResponse userEntity;
}
