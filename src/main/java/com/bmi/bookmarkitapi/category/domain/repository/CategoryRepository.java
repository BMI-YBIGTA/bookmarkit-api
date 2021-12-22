package com.bmi.bookmarkitapi.category.domain.repository;

import com.bmi.bookmarkitapi.category.domain.model.Category;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category> {

    Optional<Category> findByMainCategoryAndSubCategory(String mainCategory, String subCategory);
}
