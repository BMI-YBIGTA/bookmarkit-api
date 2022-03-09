package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkCategorySettingService {
    private final BookmarkQueryService queryService;
    private final BookmarkCommandService commandService;

    public void set(Long bookmarkId, String content, String mainCategory, String subCategory) {
        Bookmark bookmark = queryService.findById(bookmarkId);
        bookmark.setCategory(mainCategory, subCategory);
        bookmark.contentSet(content);
        commandService.save(bookmark);
    }
}
