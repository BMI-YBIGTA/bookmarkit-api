package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookMarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkCategoryQueryService implements IBookMarkCategoryQueryService {
    private final BookMarkQueryService queryService;
    @Override
    public List<BookMark> query(BookMarkQueryRequest request) {
        return queryService.query(request);
    }
}
