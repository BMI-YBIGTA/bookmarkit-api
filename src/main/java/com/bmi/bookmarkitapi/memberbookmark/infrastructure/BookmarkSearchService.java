package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookmarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkSearchService implements IBookmarkSearchService {

    private final BookmarkQueryService bookmarkQueryService;

    @Override
    public List<Bookmark> search(BookmarkSearchRequest request) {
        return bookmarkQueryService.query(request);

    }
}
