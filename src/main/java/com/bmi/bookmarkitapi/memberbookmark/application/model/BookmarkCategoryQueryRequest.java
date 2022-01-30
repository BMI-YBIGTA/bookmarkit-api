package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.List;

@Data
public class BookmarkCategoryQueryRequest {
    public List<Long> bookmarkIdList;
    public String mainCategory;

    public BookmarkCategoryQueryRequest(List<Long> bookmarkIdList, String mainCategory) {
        this.bookmarkIdList = bookmarkIdList;
        this.mainCategory = mainCategory;
    }
}
