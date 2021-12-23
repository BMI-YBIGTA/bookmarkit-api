package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;

import java.util.List;

public interface IBookMarkCategoryQueryService {
    List<BookMark> query(BookMarkQueryRequest request);
}
