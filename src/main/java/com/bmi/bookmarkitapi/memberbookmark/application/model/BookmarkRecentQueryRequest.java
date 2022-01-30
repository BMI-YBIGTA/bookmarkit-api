package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.List;

@Data
public class BookmarkRecentQueryRequest {
    public List<Long> bookmarkIdList;

    public BookmarkRecentQueryRequest(List<Long> bookmarkIdList) {
        this.bookmarkIdList = bookmarkIdList;
    }
}
