package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.dto.AssetDTO;

import com.mockproject.AuctionManagement.entity.AssetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
    @Query("SELECT new com.mockproject.AuctionManagement.dto.AssetDTO(a.idAsset, a.assetName, a.description, a.size, a.origin, a.propertyStatus, a.quantity, a.assetStatus, a.status) " +
            "FROM AssetEntity a " +
            "JOIN a.categoryAssetEntity c " +
            "WHERE c.name = :category")
    Page<AssetDTO> findAssetEntityByCategory(@Param("category") String category, Pageable pageable);
  
    @Query("""
            select a from AssetEntity a where a.status = 1
            """)
    Page<AssetEntity> findAllByStatus(Pageable pageable);
}
