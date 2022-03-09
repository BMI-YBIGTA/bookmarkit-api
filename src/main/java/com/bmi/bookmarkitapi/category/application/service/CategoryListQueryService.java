package com.bmi.bookmarkitapi.category.application.service;

import com.bmi.bookmarkitapi.category.application.model.CategoryDto;
import com.bmi.bookmarkitapi.category.domain.service.CategoryQueryService;
import com.bmi.bookmarkitapi.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryListQueryService {

    private final CategoryQueryService categoryQueryService;

    public Response.Item<Map<String, List<CategoryDto>>> getCategoryList() {
        Map<String, List<CategoryDto>> categories = categoryQueryService.findAll()
                .stream()
                .map(category -> new CategoryDto(category.getMainCategory(), category.getSubCategory()))
                .collect(Collectors.groupingBy(CategoryDto::getMainCategory));

        return new Response.Item<>(categories);
    }
}
