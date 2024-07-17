package com.mockproject.AuctionManagement.service.impl;

import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import com.mockproject.AuctionManagement.dto.MediaDTO;
import com.mockproject.AuctionManagement.dto.request.AssetRequestDTO;
import com.mockproject.AuctionManagement.dto.response.AssetResponseDTO;
import com.mockproject.AuctionManagement.dto.response.PageResponse;
import com.mockproject.AuctionManagement.entity.*;
import com.mockproject.AuctionManagement.enums.AssetStatus;
import com.mockproject.AuctionManagement.enums.TypeMedia;
import com.mockproject.AuctionManagement.repository.*;
import com.mockproject.AuctionManagement.service.AssetService;
import com.mockproject.AuctionManagement.service.MediaService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssetServiceImpl implements AssetService {

    AssetRepository assetRepository;
    UserRepository userRepository;
    MediaService mediaService;
    AssetRepositoryCustome assetRepositoryCustome;
    CategoryRepository categoryRepository;
    MediaRepository mediaRepository;

    @Override
    public AssetResponseDTO addAsset(AssetRequestDTO request, List<MultipartFile> images, MultipartFile files, MultipartFile videos) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idUser = Long.valueOf(authentication.getName());
        var seller = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User Not Found"));
        var cateAsset = categoryRepository.findCategoryAssetEntitiesByName(request.getCategoryAsset()).orElseThrow(() -> new RuntimeException("Category Not Found"));

        var asset = AssetEntity.builder()
                .assetName(request.getAssetName())
                .description(request.getDescription())
                .origin(request.getOrigin())
                .quantity(request.getQuantity())
                .size(request.getSize())
                .estimatedValue(request.getEstimatedValue())
                .propertyStatus(request.getPropertyStatus())
                .typeAuction(request.getTypeAuction())
                .assetStatus(AssetStatus.PENDING_REVIEW)
                .status(1)
                .seller(seller)
                .categoryAssetEntity(cateAsset)
                .build();

        Set<AssetMediaEntity> mediaEntityList = images.stream()
                .map(image -> {
                    String imageUrl = mediaService.upload(image);
                    return AssetMediaEntity.builder()
                            .link(imageUrl)
                            .type(TypeMedia.IMAGES)
                            .status(1)
                            .assetEntity(asset)
                            .build();
                })
                .collect(Collectors.toSet());
        mediaRepository.saveAll(mediaEntityList);
        if (videos != null) {
            String videoUrl = mediaService.upload(videos);
            var assetMedia = AssetMediaEntity.builder()
                    .assetEntity(asset)
                    .link(videoUrl)
                    .status(1)
                    .type(TypeMedia.VIDEOS)
                    .build();
            mediaRepository.save(assetMedia);
        }
        if (files != null) {
            String fileUrl = mediaService.upload(files);
            var assetMedia = AssetMediaEntity.builder()
                    .assetEntity(asset)
                    .link(fileUrl)
                    .status(1)
                    .type(TypeMedia.FILES)
                    .build();
            mediaRepository.save(assetMedia);
        }

        assetRepository.save(asset);
        return AssetResponseDTO.builder()
                .idAsset(asset.getIdAsset())
                .assetName(asset.getAssetName())
                .description(asset.getDescription())
                .estimatedValue(asset.getEstimatedValue())
                .origin(asset.getOrigin())
                .quantity(asset.getQuantity())
                .size(asset.getSize())
                .propertyStatus(asset.getPropertyStatus())
                .typeAuction(asset.getTypeAuction())
                .categoryAsset(request.getCategoryAsset())
                .images(mediaRepository.findMediaByTypeAndIdMedia(asset.getIdAsset(), TypeMedia.IMAGES).orElseThrow())
                .videos(mediaRepository.findMediaByTypeAndIdMedia(asset.getIdAsset(), TypeMedia.VIDEOS).orElseThrow().toString())
                .files(mediaRepository.findMediaByTypeAndIdMedia(asset.getIdAsset(), TypeMedia.FILES).orElseThrow().toString())
                .idSeller(idUser)
                .idCateAsset(null)
                .idWarehouse(null)
                .idAppraisers(null)
                .idUserWinner(null)
                .build();
    }

    @Override
    public AssetResponseDTO getAssetById(Long idAsset) {
        var asset = assetRepository.findById(idAsset).orElseThrow(() -> new RuntimeException("Asset Not Found"));
        return AssetResponseDTO.builder()
                .idAsset(asset.getIdAsset())
                .assetName(asset.getAssetName())
                .description(asset.getDescription())
                .estimatedValue(asset.getEstimatedValue())
                .origin(asset.getOrigin())
                .quantity(asset.getQuantity())
                .size(asset.getSize())
                .propertyStatus(asset.getPropertyStatus())
                .typeAuction(asset.getTypeAuction())
                .categoryAsset(asset.getCategoryAssetEntity().getName())
                .assetStatus(asset.getAssetStatus())
                .typeAuction(asset.getTypeAuction())
                .build();
    }

    @Override
    public PageResponse<?> getAllAssets(int pageNo, int pageSize) {
        int page = pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("idAsset").ascending());

        Page<AssetEntity> assetEntities = assetRepository.findAllByStatus(pageable);
        List<AssetResponseDTO> assetResponseDTOS = assetEntities.stream()
                .map(assetEntity -> AssetResponseDTO.builder()
                        .idAsset(assetEntity.getIdAsset())
                        .assetName(assetEntity.getAssetName())
                        .description(assetEntity.getDescription())
                        .estimatedValue(assetEntity.getEstimatedValue())
                        .origin(assetEntity.getOrigin())
                        .quantity(assetEntity.getQuantity())
                        .size(assetEntity.getSize())
                        .propertyStatus(assetEntity.getPropertyStatus())
                        .typeAuction(assetEntity.getTypeAuction())
                        .categoryAsset(Optional.ofNullable(assetEntity.getCategoryAssetEntity()).map(CategoryAssetEntity::getName).orElse(null))
                        .assetStatus(assetEntity.getAssetStatus())
                        .idSeller(Optional.ofNullable(assetEntity.getSeller()).map(UserEntity::getIdUser).orElse(null))
                        .idAppraisers(Optional.ofNullable(assetEntity.getAppraisersEntity()).map(AppraisersEntity::getIdAppraisers).orElse(null))
                        .idWarehouse(Optional.ofNullable(assetEntity.getWarehouseEntity()).map(WarehouseEntity::getIdWarehouse).orElse(null))
                        .idUserWinner(Optional.ofNullable(assetEntity.getUserWinner()).map(UserEntity::getIdUser).orElse(null))
                        .build()
                )
                .collect(Collectors.toList());

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(assetEntities.getTotalPages())
                .items(assetResponseDTOS)
                .build();
    }

    @Override
    public PageResponse<?> getAllAssetByIdSeller(int idSeller, int pageNo, int pageSize) {
        return assetRepositoryCustome.findAllAssetByIdSerller(idSeller, pageNo, pageSize);
    }

    @Override
    public void deleteAssetById(Long idAsset) {
        AssetEntity assetEntity = assetRepository.findById(idAsset).orElseThrow(() -> new RuntimeException("Asset Not Found"));
        assetEntity.setStatus(0);
        assetRepository.save(assetEntity);
    }

    @Override
    public void updateAssetStatus(Long idAsset) {
        AssetEntity assetEntity = assetRepository.findById(idAsset).orElseThrow(() -> new RuntimeException("Asset Not Found"));
        assetEntity.setAssetStatus(AssetStatus.UNDER_EVALUATION);
    }

    @Override
    public AssetResponseDTO updateAsset(Long idAsset, AssetRequestDTO request, List<MultipartFile> images, MultipartFile videos, MultipartFile files) {
        var cateAsset = categoryRepository.findCategoryAssetEntitiesByName(request.getCategoryAsset()).orElseThrow(() -> new RuntimeException("Category Not Found"));

        var assetUpdate = assetRepository.findById(idAsset)
                .map(asset -> {
                    asset.setAssetName(request.getAssetName());
                    asset.setDescription(request.getDescription());
                    asset.setEstimatedValue(request.getEstimatedValue());
                    asset.setOrigin(request.getOrigin());
                    asset.setQuantity(request.getQuantity());
                    asset.setSize(request.getSize());
                    asset.setPropertyStatus(request.getPropertyStatus());
                    asset.setTypeAuction(request.getTypeAuction());
                    asset.setCategoryAssetEntity(cateAsset);
                    return assetRepository.save(asset);
                })
                .orElseThrow(() -> new RuntimeException("Asset Not Found"));
        //Delete by converting status = 0
        deleteAssetMediaByTypeAndIdMedia(idAsset, TypeMedia.IMAGES);

        // Save image
        Set<AssetMediaEntity> mediaEntityList = images.stream()
                .map(image -> {
                    String imageUrl = mediaService.upload(image);
                    return AssetMediaEntity.builder()
                            .link(imageUrl)
                            .type(TypeMedia.IMAGES)
                            .status(1)
                            .assetEntity(assetUpdate)
                            .build();
                })
                .collect(Collectors.toSet());
        mediaRepository.saveAll(mediaEntityList);

        if (videos != null) {
            deleteAssetMediaByTypeAndIdMedia(idAsset, TypeMedia.VIDEOS);
            String videoUrl = mediaService.upload(videos);
            var assetMedia = AssetMediaEntity.builder()
                    .assetEntity(assetUpdate)
                    .link(videoUrl)
                    .status(1)
                    .type(TypeMedia.VIDEOS)
                    .build();
            mediaRepository.save(assetMedia);
        }
        if (files != null) {
            deleteAssetMediaByTypeAndIdMedia(idAsset, TypeMedia.FILES);
            String fileUrl = mediaService.upload(files);
            var assetMedia = AssetMediaEntity.builder()
                    .assetEntity(assetUpdate)
                    .link(fileUrl)
                    .status(1)
                    .type(TypeMedia.FILES)
                    .build();
            mediaRepository.save(assetMedia);
        }

        assetRepository.save(assetUpdate);
        return AssetResponseDTO.builder()
                .idAsset(assetUpdate.getIdAsset())
                .assetName(assetUpdate.getAssetName())
                .description(assetUpdate.getDescription())
                .estimatedValue(assetUpdate.getEstimatedValue())
                .origin(assetUpdate.getOrigin())
                .quantity(assetUpdate.getQuantity())
                .size(assetUpdate.getSize())
                .propertyStatus(assetUpdate.getPropertyStatus())
                .typeAuction(assetUpdate.getTypeAuction())
                .categoryAsset(request.getCategoryAsset())
                .images(mediaRepository.findMediaByTypeAndIdMedia(assetUpdate.getIdAsset(), TypeMedia.IMAGES).orElseThrow())
                .videos(mediaRepository.findMediaByTypeAndIdMedia(assetUpdate.getIdAsset(), TypeMedia.VIDEOS).orElseThrow().toString())
                .files(mediaRepository.findMediaByTypeAndIdMedia(assetUpdate.getIdAsset(), TypeMedia.FILES).orElseThrow().toString())
                .idCateAsset(null)
                .idWarehouse(null)
                .idAppraisers(null)
                .idUserWinner(null)
                .build();
    }

    private void deleteAssetMediaByTypeAndIdMedia(Long idAsset, TypeMedia typeMedia) {
        List<AssetMediaEntity> assetMediaEntities = mediaRepository.findAssetMediaByTypeAndIdMedia(idAsset, typeMedia).orElseThrow(() -> new RuntimeException("{typeMedia} Not Found"));
        assetMediaEntities.forEach(image -> image.setStatus(0));
        mediaRepository.saveAll(assetMediaEntities);
    }

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
