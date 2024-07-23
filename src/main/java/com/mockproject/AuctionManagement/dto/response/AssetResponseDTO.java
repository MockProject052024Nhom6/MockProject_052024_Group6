package com.mockproject.AuctionManagement.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mockproject.AuctionManagement.enums.AssetStatus;
import com.mockproject.AuctionManagement.enums.TypeAuction;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetResponseDTO {

    private Long idAsset;
    private String assetName;
    private String description;
    private Double estimatedValue;
    private String size;
    private String origin;
    private String propertyStatus;
    private String categoryAsset;
    private Integer quantity;
    private TypeAuction typeAuction;
    private List<String> images;
    private String files;
    private String videos;
    private AssetStatus assetStatus;
    private Integer idAppraisers;
    private Long idCateAsset;
    private Long idSeller;
    private Long idUserWinner;
    private Integer idWarehouse;
}

