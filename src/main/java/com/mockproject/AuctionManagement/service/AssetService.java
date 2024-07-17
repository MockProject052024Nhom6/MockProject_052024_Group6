package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssetService {
    Page<AssetByCategoryDTO> getAllAssetsByCategory(Pageable pageable);

    Page<AssetDTO> getAssetsByCategory(String category, Pageable pageable);

}
