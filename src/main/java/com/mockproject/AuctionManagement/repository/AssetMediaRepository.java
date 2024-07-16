package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.AssetMediaEntity;
import com.mockproject.AuctionManagement.enums.TypeMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetMediaRepository extends JpaRepository<AssetMediaEntity, Long> {

    @Query("""
            select m.link
            from AssetMediaEntity m join m.assetEntity a
            where a.idAsset = :idAsset and m.type = :type and m.status = 1
            """)
    Optional<List<String>> findMediaByTypeAndIdMedia(@Param("idAsset") Long idAsset, @Param("type") TypeMedia type);


    @Query("select a from AssetMediaEntity a where a.assetEntity.idAsset = :idAsset and a.status = 1")
    Optional<List<AssetMediaEntity>> findByidAsset(@Param("idAsset") Long idAsset);

    @Query("""
            select m
            from AssetMediaEntity m join m.assetEntity a
            where a.idAsset = :idAsset and m.type = :type and m.status = 1
            """)
    Optional<List<AssetMediaEntity>> findAssetMediaByTypeAndIdMedia(@Param("idAsset") Long idAsset, @Param("type") TypeMedia type);
}
