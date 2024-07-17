package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mockproject.AuctionManagement.dto.request.AssetRequestDTO;
import com.mockproject.AuctionManagement.dto.response.AssetResponseDTO;
import com.mockproject.AuctionManagement.dto.response.PageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface AssetService {

    AssetResponseDTO addAsset(AssetRequestDTO request, List<MultipartFile> images, MultipartFile files, MultipartFile videos);

    AssetResponseDTO getAssetById(Long idAsset);

    PageResponse<?> getAllAssets(int pageNo, int pageSize);

    PageResponse<?> getAllAssetByIdSeller(int idSeller, int pageNo, int pageSize);

    void deleteAssetById(Long idAsset);

    void updateAssetStatus(Long idAsset);

    AssetResponseDTO updateAsset(Long idAsset, AssetRequestDTO request, List<MultipartFile> images, MultipartFile videos, MultipartFile files);
  
    Page<AssetByCategoryDTO> getAllAssetsByCategory(Pageable pageable);

    Page<AssetDTO> getAssetsByCategory(String category, Pageable pageable);

}
