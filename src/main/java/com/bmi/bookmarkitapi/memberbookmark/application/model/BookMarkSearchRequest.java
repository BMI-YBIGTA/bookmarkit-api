package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class BookMarkSearchRequest {
    public List<Long> bookMarkIdList;
    public List<Long> titleContainsBookMarkIdList;
    public String searchText;
    public Pageable pageable;

    public BookMarkSearchRequest(List<Long> bookMarkIdList, List<Long> titleContainsBookMarkIdList, String searchText, Pageable pageable) {
        this.bookMarkIdList = bookMarkIdList;
        this.titleContainsBookMarkIdList = titleContainsBookMarkIdList;
        this.searchText = searchText;
        this.pageable = pageable;
    }
}
