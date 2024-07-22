package com.mockproject.AuctionManagement.dto.request;

import com.mockproject.AuctionManagement.enums.TypeAuction;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetRequestDTO {

    @NotBlank(message = "Asset Name is not Blank")
    private String assetName;

    @NotBlank(message = "Asset Name is not Blank")
    private String description;

    @NotNull(message = "Asset Name cannot be null")
    @Min(value = 0, message = "The value must be greater than 0")
    private Double estimatedValue;

    @NotBlank(message = "Size cannot be blank")
    private String size;

    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotBlank(message = "Property Status cannot be blank")
    private String propertyStatus;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "The quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Type Auction cannot be null")
    private TypeAuction typeAuction;

    @NotBlank(message = "Category Asset cannot be blank")
    private String categoryAsset;
}
