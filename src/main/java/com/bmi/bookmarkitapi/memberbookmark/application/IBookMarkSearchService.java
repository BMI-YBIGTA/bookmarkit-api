package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookMarkSearchService {
    List<BookMark> search(BookMarkSearchRequest request);
}
