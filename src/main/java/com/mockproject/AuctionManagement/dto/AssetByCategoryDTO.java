package com.mockproject.AuctionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetByCategoryDTO {
    private String categoryName;
    private List<AssetDTO> assets;
}
