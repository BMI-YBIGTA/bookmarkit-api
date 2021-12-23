package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Data
public class BookMarkQueryRequest {
    public List<Long> bookMarkIdList;
    public Optional<String> category;

    public BookMarkQueryRequest(List<Long> bookMarkIdList, Optional<String> category) {
        this.bookMarkIdList = bookMarkIdList;
        this.category = category;
    }
}
