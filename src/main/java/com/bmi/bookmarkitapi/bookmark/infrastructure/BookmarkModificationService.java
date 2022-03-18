package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class BookmarkModificationService {

    private final BookmarkQueryService bookmarkQueryService;

    public void request(Long bookmarkId) {
        Bookmark bookmark = bookmarkQueryService.findById(bookmarkId);
        bookmark.modifyStatus(BookmarkStatus.REQUESTING);
    }

    public void complete(Long bookmarkId, String content, String mainCategory, String subCategory) {
        Bookmark bookmark = bookmarkQueryService.findById(bookmarkId);
        bookmark.setContent(content);
        bookmark.setCategory(mainCategory, subCategory);
        bookmark.modifyStatus(BookmarkStatus.COMPLETED);
    }
}
