package com.bmi.bookmarkitapi.category.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    public String mainCategory;
    public String subCategory;
}
