package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkSearchService {

    private final BookmarkQueryService bookmarkQueryService;

    public List<Bookmark> search(
            List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, Pageable pageable
    ) {
        return bookmarkQueryService.search(bookmarkIds, titleSearchedBookmarkIds, searchText, pageable);
    }
}
