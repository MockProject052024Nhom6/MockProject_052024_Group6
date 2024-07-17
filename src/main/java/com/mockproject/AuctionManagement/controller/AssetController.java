package com.mockproject.AuctionManagement.controller;


import com.mockproject.AuctionManagement.dto.AssetByCategoryDTO;
import com.mockproject.AuctionManagement.dto.AssetDTO;
import com.mockproject.AuctionManagement.dto.CategoryQuantityDTO;
import com.mockproject.AuctionManagement.service.AssetService;
import com.mockproject.AuctionManagement.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
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


}
