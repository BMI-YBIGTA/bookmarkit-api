package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.List;

@Data
public class BookMarkRecentQueryRequest {
    public List<Long> bookMarkIdList;

    public BookMarkRecentQueryRequest(List<Long> bookMarkIdList) {
        this.bookMarkIdList = bookMarkIdList;
    }
}
