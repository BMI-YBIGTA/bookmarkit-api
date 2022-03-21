package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkSearchService {

    private final BookmarkQueryService bookmarkQueryService;

    public List<Bookmark> search(
            Long bookmarkId, List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, int pageSize
    ) {
        return bookmarkQueryService.search(bookmarkId, bookmarkIds, titleSearchedBookmarkIds, searchText, pageSize);
    }
}
