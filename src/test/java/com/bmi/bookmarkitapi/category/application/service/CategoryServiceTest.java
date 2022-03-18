package com.bmi.bookmarkitapi.category.application.service;

import com.bmi.bookmarkitapi.category.application.model.response.CategoryListResponse;
import com.bmi.bookmarkitapi.category.domain.model.Category;
import com.bmi.bookmarkitapi.category.domain.service.CategoryQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryQueryService categoryQueryService;
    @InjectMocks
    CategoryServiceImpl categoryService;

    @DisplayName("카테고리 목록을 조회하면 메인 카테고리로 묶인 카테고리 목록을 반환한다")
    @Test
    void getCategoryList() {
        String mainCategory1 = "Language";
        String subCategory1 = "C";
        String subCategory2 = "Java";
        String mainCategory2 = "CS";
        String subCategory3 = "Algorithm";
        Category category1 = new Category(mainCategory1, subCategory1);
        Category category2 = new Category(mainCategory1, subCategory2);
        Category category3 = new Category(mainCategory2, subCategory3);
        List<Category> categories = Arrays.asList(category1, category2, category3);

        when(categoryQueryService.findAll()).thenReturn(categories);

        List<CategoryListResponse> result = categoryService.getCategoryList();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).filteredOn(category -> category.getMainCategory().equals(mainCategory1))
                .flatExtracting("subCategories")
                .extracting("subCategory")
                .containsExactlyInAnyOrder(subCategory1, subCategory2);
        assertThat(result).filteredOn(category -> category.getMainCategory().equals(mainCategory2))
                .flatExtracting("subCategories")
                .extracting("subCategory")
                .containsExactly(subCategory3);
        verify(categoryQueryService).findAll();
    }
}
