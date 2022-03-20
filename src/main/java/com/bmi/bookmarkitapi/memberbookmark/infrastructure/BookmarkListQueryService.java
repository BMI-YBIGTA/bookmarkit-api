package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkListQueryService {

    private final BookmarkQueryService bookmarkQueryService;

    public List<Bookmark> getBookmarksByCategory(List<Long> bookmarkIds, String mainCategory) {
        return bookmarkQueryService.findByMainCategory(bookmarkIds, mainCategory);
    }

    public List<Bookmark> getRecentBookmarks(List<Long> bookmarkIds) {
        return  bookmarkQueryService.findRecent20Bookmarks(bookmarkIds);
    }
}
