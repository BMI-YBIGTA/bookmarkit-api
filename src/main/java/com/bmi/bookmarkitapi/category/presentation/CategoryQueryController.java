package com.bmi.bookmarkitapi.category.presentation;

import com.bmi.bookmarkitapi.category.application.model.response.CategoryListResponse;
import com.bmi.bookmarkitapi.category.application.service.CategoryService;
import com.bmi.bookmarkitapi.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryQueryController {

    private final CategoryService categoryService;

    @GetMapping
    public Response.ItemList<CategoryListResponse> getCategories() {
        return new Response.ItemList<>(categoryService.getCategories());
    }
}
