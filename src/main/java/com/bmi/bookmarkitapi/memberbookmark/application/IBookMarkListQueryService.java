package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkRecentQueryRequest;

import java.util.List;

public interface IBookMarkListQueryService {
    List<BookMark> query(BookMarkCategoryQueryRequest request);
    List<BookMark> query(BookMarkRecentQueryRequest request);
}
