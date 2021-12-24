package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.List;

@Data
public class BookMarkCategoryQueryRequest {
    public List<Long> bookMarkIdList;
    public String mainCategory;

    public BookMarkCategoryQueryRequest(List<Long> bookMarkIdList, String mainCategory) {
        this.bookMarkIdList = bookMarkIdList;
        this.mainCategory = mainCategory;
    }
}
