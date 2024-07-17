package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.dto.MediaDTO;
import com.mockproject.AuctionManagement.entity.AssetMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends JpaRepository<AssetMediaEntity, Long> {
    @Query("SELECT new com.mockproject.AuctionManagement.dto.MediaDTO(m.link, m.type, m.description) " +
            "FROM AssetMediaEntity m WHERE m.idMedia = :assetId")
    List<MediaDTO> findMediaByAssetId(@Param("assetId") Long assetId);
}
