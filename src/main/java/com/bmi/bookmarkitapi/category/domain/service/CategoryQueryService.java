package com.bmi.bookmarkitapi.category.domain.service;

import com.bmi.bookmarkitapi.category.domain.model.Category;
import com.bmi.bookmarkitapi.category.domain.repository.CategoryRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryQueryService extends BaseQueryService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryQueryService(CategoryRepository categoryRepository) {
        super(categoryRepository, new ResourceNotFoundException());
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> query(String mainCategory, String subCategory) {
        return categoryRepository.findByMainCategoryAndSubCategory(mainCategory, subCategory);
    }
}
