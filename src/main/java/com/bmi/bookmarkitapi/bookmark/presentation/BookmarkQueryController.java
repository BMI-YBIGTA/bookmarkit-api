package com.bmi.bookmarkitapi.bookmark.presentation;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.common.BaseQueryController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookmark")
public class BookmarkQueryController extends BaseQueryController<Bookmark> {
    public BookmarkQueryController(BookmarkQueryService queryService) {
        super(queryService);
    }
}
