package com.bmi.bookmarkitapi.category.application.service;

import com.bmi.bookmarkitapi.category.application.model.response.CategoryListResponse;
import com.bmi.bookmarkitapi.category.domain.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryQueryService categoryQueryService;

    @Override
    public List<CategoryListResponse> getCategoryList() {
        Map<String, List<CategoryListResponse.CategoryDto>> categories = categoryQueryService.findAll()
                .stream()
                .map(category -> new CategoryListResponse.CategoryDto(category.getMainCategory(), category.getSubCategory()))
                .collect(Collectors.groupingBy(CategoryListResponse.CategoryDto::getMainCategory));

        return categories.entrySet()
                .stream()
                .map(entry -> new CategoryListResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
