package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkStatusModificationService {
    private final BookmarkQueryService queryService;

    @Transactional
    public void request(Long bookmarkId) {
        Bookmark bookmark = queryService.findById(bookmarkId);
        bookmark.request();
    }

    @Transactional
    public void complete(Long bookmarkId) {
        Bookmark bookmark = queryService.findById(bookmarkId);
        bookmark.complete();
    }
}
