package com.mockproject.AuctionManagement.repository;


import com.mockproject.AuctionManagement.dto.CategoryQuantityDTO;
import com.mockproject.AuctionManagement.entity.CategoryAssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryAssetEntity, Integer> {
    @Query("SELECT new com.mockproject.AuctionManagement.dto.CategoryQuantityDTO(c.idCateAsset, c.name, COUNT(a.idAsset)) " +
            "FROM AssetEntity a " +
            "JOIN a.categoryAssetEntity c " +
            "GROUP BY c.idCateAsset, c.name")
    List<CategoryQuantityDTO> getCategoryQuantity();

    Optional<CategoryAssetEntity> findCategoryAssetEntitiesByName(String categoryId);
}
