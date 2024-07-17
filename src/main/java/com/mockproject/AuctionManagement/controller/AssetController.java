package com.mockproject.AuctionManagement.controller;

import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import com.mockproject.AuctionManagement.dto.CategoryQuantityDTO;
import com.mockproject.AuctionManagement.dto.request.AssetRequestDTO;
import com.mockproject.AuctionManagement.dto.response.AssetResponseDTO;
import com.mockproject.AuctionManagement.dto.response.ResponseData;
import com.mockproject.AuctionManagement.dto.response.ResponseError;
import com.mockproject.AuctionManagement.service.AssetService;
import com.mockproject.AuctionManagement.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Asset", description = "Asset API")
public class AssetController {

    private final AssetService assetService;
    private final CategoryService categoryService;

    @Operation(
            summary = "Get assets by category",
            description = "Get assets by category"
    )
    @GetMapping("/categories")
    public Page<AssetByCategoryDTO> getAllAssetsByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return assetService.getAllAssetsByCategory(pageable);
    }

    @GetMapping("/category/{category}")
    public Page<AssetDTO> getAssetsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return assetService.getAssetsByCategory(category, pageable);
    }

    @GetMapping("/assets-catergory")
    public List<CategoryQuantityDTO> getAssetsByCategory() {
        return categoryService.getCategoryQuantity();
    }

    @Operation(summary = "create new asset")
    @PostMapping()
    public ResponseData<?> addAsset(@RequestPart(value = "images", required = false) List<MultipartFile> images,
                                    @RequestPart(value = "videos", required = false) MultipartFile videos,
                                    @RequestPart(value = "files", required = false) MultipartFile files,
                                    @Valid @ModelAttribute AssetRequestDTO request) {
        try{
            AssetResponseDTO response = assetService.addAsset(request, images, files, videos);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Asset added successfully", response);
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "add asset failed");
        }
    }

    @Operation(summary = "get asset by id asset")
    @GetMapping("/{idAsset}")
    public ResponseData<?> getAssetById(@PathVariable() Long idAsset) {
        try{
            AssetResponseDTO response = assetService.getAssetById(idAsset);
            return new ResponseData<>(HttpStatus.OK.value(), "Asset get successfully", response);
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "get asset by id failed");
        }
    }

    @Operation(summary = "get all asset")
    @GetMapping()
    public ResponseData<?> getAllAssets(@Min(1) @RequestParam(required = false, defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "Asset get successfully", assetService.getAllAssets(pageNo, pageSize));
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "get all assets failed");
        }
    }

    @Operation(summary = "get asset by id seller")
    @GetMapping("/sellers/{idSeller}")
    public ResponseData<?> getAllAssetsByIdSeller(@PathVariable() int idSeller,
                                                  @Min(1) @RequestParam(required = false, defaultValue = "1") int pageNo,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize) {
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "Asset get successfully", assetService.getAllAssetByIdSeller(idSeller, pageNo, pageSize));
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "get all assets failed");
        }
    }

    @Operation(summary = "delete asset by id asset")
    @DeleteMapping("/{idAsset}")
    public ResponseData<?> deleteAssetById(@PathVariable Long idAsset) {
        try{
            assetService.deleteAssetById(idAsset);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete successfully");
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete assets failed");
        }
    }

    @Operation(summary = "update asset status", description = "Reviewing property registration applications")
    @PatchMapping("/{idAsset}")
    public ResponseData<?> updateAssetStatus(@PathVariable Long idAsset) {
        try{
            assetService.updateAssetStatus(idAsset);
            return new ResponseData<>(HttpStatus.OK.value(), "PENDING REVIEW successfully");
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "PENDING REVIEW failed");
        }
    }

    @Operation(summary = "update information asset")
    @PutMapping("/{idAsset}")
    public ResponseData<?> updateAsset(@PathVariable Long idAsset,
                                       @Valid @ModelAttribute AssetRequestDTO request,
                                       @RequestPart(value = "images", required = false) List<MultipartFile> images,
                                       @RequestPart(value = "videos", required = false) MultipartFile videos,
                                       @RequestPart(value = "files", required = false) MultipartFile files) {
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "Update successfully", assetService.updateAsset(idAsset, request, images, videos, files));
        }catch (Exception e){
            log.error("errorMessage{}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update failed");
        }
    }

}
