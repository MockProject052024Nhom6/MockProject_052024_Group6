package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.AssetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    @Query("""
            select a from AssetEntity a where a.status = 1
            """)
    Page<AssetEntity> findAllByStatus(Pageable pageable);

}
