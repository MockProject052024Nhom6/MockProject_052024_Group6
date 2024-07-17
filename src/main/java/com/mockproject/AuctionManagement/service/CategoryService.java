package com.mockproject.AuctionManagement.service;

import com.mockproject.AuctionManagement.dto.CategoryQuantityDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryQuantityDTO> getCategoryQuantity();
}
