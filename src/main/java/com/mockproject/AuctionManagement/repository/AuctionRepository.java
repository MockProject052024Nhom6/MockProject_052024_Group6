package com.mockproject.AuctionManagement.repository;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNull;
import com.mockproject.AuctionManagement.entity.AuctionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {

    Optional<AuctionEntity> findByIdAuctionAndUserEntity_IdUser(Long idAuction, Long idUser);

    Page<AuctionEntity> findAll(@NonNull Pageable pageable);
}