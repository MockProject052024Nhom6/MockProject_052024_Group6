package com.mockproject.AuctionManagement.mapper;

import com.mockproject.AuctionManagement.dto.request.AuctionRequestCreate;
import com.mockproject.AuctionManagement.dto.request.AuctionRequestUpdate;
import com.mockproject.AuctionManagement.dto.response.AuctionResponse;
import com.mockproject.AuctionManagement.entity.AuctionEntity;
import com.mockproject.AuctionManagement.entity.UserEntity;
import com.mockproject.AuctionManagement.exception.AppException;
import com.mockproject.AuctionManagement.exception.ErrorCode;
import com.mockproject.AuctionManagement.repository.UserRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuctionMapper {
    AuctionEntity toAuction(AuctionRequestCreate request);

    @Mapping(source = "userEntity.idUser", target = "userEntity.idUser")
    AuctionResponse toAuctionResponse(AuctionEntity auction);
    @Mapping(source = "idUser", target = "userEntity.idUser")
    void updateAuctionFromDto(AuctionRequestUpdate dto, @MappingTarget AuctionEntity entity);

}