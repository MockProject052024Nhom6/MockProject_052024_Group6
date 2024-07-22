package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.UserHasAuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRegisterRepository extends JpaRepository<UserHasAuctionEntity, Integer> {
}
