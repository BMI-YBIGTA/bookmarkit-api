package com.bmi.bookmarkitapi.category.presentation;

import com.bmi.bookmarkitapi.category.application.model.CategoryDto;
import com.bmi.bookmarkitapi.category.application.service.CategoryListQueryService;
import com.bmi.bookmarkitapi.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryQueryController {

    private final CategoryListQueryService categoryListQueryService;

    @GetMapping
    public Response.Item<Map<String, List<CategoryDto>>> list() {
        return categoryListQueryService.getCategoryList();
    }
}
