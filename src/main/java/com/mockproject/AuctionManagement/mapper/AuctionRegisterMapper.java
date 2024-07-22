package com.mockproject.AuctionManagement.mapper;

import com.mockproject.AuctionManagement.dto.request.AuctionRegisterRequest;
import com.mockproject.AuctionManagement.dto.response.AuctionRegisterResponse;
import com.mockproject.AuctionManagement.entity.UserHasAuctionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuctionRegisterMapper {
    @Mapping(source = "request.idAuction", target = "auctionEntity.idAuction")
    @Mapping(source = "request.idUser", target = "userEntity.idUser")
    UserHasAuctionEntity toRegisterAuction(AuctionRegisterRequest request);

    @Mapping(source = "entity.auctionEntity.idAuction", target = "auctionEntity.idAuction")
    @Mapping(source = "entity.deposits", target = "deposits")
    @Mapping(source = "entity.depositsDate", target = "depositsDate")
    @Mapping(source = "entity.userHasAuctionStatus", target = "userHasAuctionStatus")
    @Mapping(source = "entity.status", target = "status")
    @Mapping(source = "entity.auctionEntity", target = "auctionEntity")
    @Mapping(source = "entity.userEntity", target = "userEntity")
    AuctionRegisterResponse toResponse(UserHasAuctionEntity entity);

}
