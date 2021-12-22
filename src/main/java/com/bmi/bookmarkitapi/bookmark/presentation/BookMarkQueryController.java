package com.bmi.bookmarkitapi.bookmark.presentation;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.common.BaseQueryController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmark")
public class BookMarkQueryController extends BaseQueryController<BookMark> {
    public BookMarkQueryController(BookMarkQueryService queryService) {
        super(queryService);
    }
}
