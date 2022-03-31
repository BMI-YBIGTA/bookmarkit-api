package com.bmi.bookmarkitapi.category.application;

import com.bmi.bookmarkitapi.category.application.model.response.CategoryListResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryListResponse> getCategories();
}
