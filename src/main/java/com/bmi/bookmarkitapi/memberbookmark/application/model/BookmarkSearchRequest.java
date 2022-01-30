package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class BookmarkSearchRequest {
    public List<Long> bookmarkIdList;
    public List<Long> titleContainsBookmarkIdList;
    public String searchText;
    public Pageable pageable;

    public BookmarkSearchRequest(List<Long> bookmarkIdList, List<Long> titleContainsBookmarkIdList, String searchText, Pageable pageable) {
        this.bookmarkIdList = bookmarkIdList;
        this.titleContainsBookmarkIdList = titleContainsBookmarkIdList;
        this.searchText = searchText;
        this.pageable = pageable;
    }
}
