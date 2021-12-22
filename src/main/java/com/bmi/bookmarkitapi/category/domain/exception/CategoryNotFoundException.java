package com.bmi.bookmarkitapi.category.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super("카테고리가 존재하지 않습니다.");
    }
}
