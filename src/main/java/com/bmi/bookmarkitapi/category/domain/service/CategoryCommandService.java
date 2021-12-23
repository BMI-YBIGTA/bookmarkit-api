package com.bmi.bookmarkitapi.category.domain.service;

import com.bmi.bookmarkitapi.category.domain.model.Category;
import com.bmi.bookmarkitapi.category.domain.repository.CategoryRepository;
import com.bmi.bookmarkitapi.common.BaseCommandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryCommandService extends BaseCommandService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryCommandService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }
}
