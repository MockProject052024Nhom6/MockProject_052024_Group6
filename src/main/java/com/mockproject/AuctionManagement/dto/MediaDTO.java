package com.mockproject.AuctionManagement.dto;

import com.mockproject.AuctionManagement.enums.TypeMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {
    private String link;
    private TypeMedia type;
    private String description;
}
