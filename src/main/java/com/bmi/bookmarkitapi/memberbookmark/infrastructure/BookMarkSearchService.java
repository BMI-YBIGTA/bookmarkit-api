package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookMarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkSearchService implements IBookMarkSearchService {
    private final BookMarkQueryService bookMarkQueryService;

    @Override
    public List<BookMark> search(BookMarkSearchRequest request) {
        return bookMarkQueryService.query(request);

    }
}
