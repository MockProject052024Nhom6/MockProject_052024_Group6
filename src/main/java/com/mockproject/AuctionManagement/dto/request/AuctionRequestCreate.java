package com.mockproject.AuctionManagement.dto.request;

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
public class AuctionRequestCreate {
    String auctionName;
    String address;
    Integer numberOfParticipants;
    Date startTime;
    Date endTime;
    TypeAuction typeAuction;
    AuctionStatus auctionStatus;
    Integer status;
    Long idUser;
}
