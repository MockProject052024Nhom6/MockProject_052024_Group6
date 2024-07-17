package com.mockproject.AuctionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQuantityDTO {
    private Integer idCateAsset;
    private String name;
    private Long quantity;
}
