package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookMarkListQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkRecentQueryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkListQueryService implements IBookMarkListQueryService {
    private final BookMarkQueryService queryService;

    @Override
    public List<BookMark> query(BookMarkCategoryQueryRequest request) {
        return queryService.query(request);
    }

    @Override
    public List<BookMark> query(BookMarkRecentQueryRequest request) {
        return  queryService.query(request);
    }
}
