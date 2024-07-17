package com.mockproject.AuctionManagement.service.impl;

import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import com.mockproject.AuctionManagement.dto.MediaDTO;
import com.mockproject.AuctionManagement.entity.AssetEntity;
import com.mockproject.AuctionManagement.entity.CategoryAssetEntity;
import com.mockproject.AuctionManagement.repository.AssetRepository;
import com.mockproject.AuctionManagement.repository.CategoryRepository;
import com.mockproject.AuctionManagement.repository.MediaRepository;
import com.mockproject.AuctionManagement.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final CategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;


    @Override
    public Page<AssetByCategoryDTO> getAllAssetsByCategory(Pageable pageable) {
        ArrayList<CategoryAssetEntity> categoryAssetEntities = new ArrayList<>(categoryRepository.findAll());
        List<AssetByCategoryDTO> assetByCategoryDTOS = new ArrayList<>();
        for (CategoryAssetEntity categoryAssetEntity : categoryAssetEntities) {
            Page<AssetDTO> assetPage = assetRepository.findAssetEntityByCategory(categoryAssetEntity.getName(), pageable);
            List<AssetDTO> assetDTOS = assetPage.getContent();
            for (AssetDTO assetDTO : assetDTOS) {
                List<MediaDTO> mediaList = mediaRepository.findMediaByAssetId(assetDTO.getIdAsset());
                assetDTO.setMediaList(mediaList); // Thêm danh sách hình ảnh vào DTO
            }
            assetByCategoryDTOS.add(new AssetByCategoryDTO(categoryAssetEntity.getName(), assetPage.getContent()));
        }
        int start = (int) pageable.getOffset();
        int end = Math.max((start + pageable.getPageSize()), assetByCategoryDTOS.size());

        List<AssetByCategoryDTO> sublist = assetByCategoryDTOS.subList(start, end);

        return new PageImpl<>(sublist, pageable, assetByCategoryDTOS.size());
    }

    @Override
    public Page<AssetDTO> getAssetsByCategory(String category, Pageable pageable) {
        Page<AssetDTO> page = assetRepository.findAssetEntityByCategory(category, pageable);
        List<AssetDTO> assetDTOS = page.getContent();
        for (AssetDTO assetDTO : assetDTOS) {
            List<MediaDTO> mediaList = mediaRepository.findMediaByAssetId(assetDTO.getIdAsset());
            assetDTO.setMediaList(mediaList);
        }
        return page;
    }

}
