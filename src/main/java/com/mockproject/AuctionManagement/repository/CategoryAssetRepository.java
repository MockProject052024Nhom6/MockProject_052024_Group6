package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.entity.CategoryAssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryAssetRepository extends JpaRepository<CategoryAssetEntity, Integer> {

    Optional<CategoryAssetEntity> findCategoryAssetEntitiesByName(String categoryId);
}
