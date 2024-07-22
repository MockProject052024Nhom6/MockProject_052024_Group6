package com.mockproject.AuctionManagement.service.impl;

import com.mockproject.AuctionManagement.dto.CategoryQuantityDTO;
import com.mockproject.AuctionManagement.repository.CategoryRepository;
import com.mockproject.AuctionManagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryQuantityDTO> getCategoryQuantity() {
        return categoryRepository.getCategoryQuantity();
    }
}
