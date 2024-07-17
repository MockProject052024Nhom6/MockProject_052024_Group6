package com.mockproject.AuctionManagement.dto;

import com.mockproject.AuctionManagement.enums.AssetStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
    private Long idAsset;
    private String assetName;
    private String description;
    private String size;
    private String origin;
    private String propertyStatus;
    private String quantity;
    private AssetStatus assetStatus;
    private Integer status;
    private List<MediaDTO> mediaList;

    public AssetDTO(Long idAsset, String assetName, String description, String size, String origin, String propertyStatus, String quantity, AssetStatus assetStatus, Integer status) {
        this.idAsset = idAsset;
        this.assetName = assetName;
        this.description = description;
        this.size = size;
        this.origin = origin;
        this.propertyStatus = propertyStatus;
        this.quantity = quantity;
        this.assetStatus = assetStatus;
        this.status = status;
    }
}
