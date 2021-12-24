package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Data
public class BookMarkCategoryQueryRequest {
    public List<Long> bookMarkIdList;
    public Optional<String> mainCategory;

    public BookMarkCategoryQueryRequest(List<Long> bookMarkIdList, Optional<String> mainCategory) {
        this.bookMarkIdList = bookMarkIdList;
        this.mainCategory = mainCategory;
    }
}
