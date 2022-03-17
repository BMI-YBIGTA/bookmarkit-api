package com.bmi.bookmarkitapi.category.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryListResponse {
    private String mainCategory;
    private List<CategoryDto> subCategories;

    @Getter
    @AllArgsConstructor
    public static class CategoryDto {
        private String mainCategory;
        private String subCategory;
    }
}
