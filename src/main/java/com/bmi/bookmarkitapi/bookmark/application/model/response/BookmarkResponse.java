package com.bmi.bookmarkitapi.bookmark.application.model.response;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.Getter;

@Getter
public class BookmarkResponse {
    private final String link;
    private final String content;
    private final String mainCategory;
    private final String subCategory;
    private final BookmarkStatus status;

    public BookmarkResponse(Bookmark bookmark) {
        link = bookmark.getLink();
        content = bookmark.getContent();
        mainCategory = bookmark.getMainCategory();
        subCategory = bookmark.getSubCategory();
        status = bookmark.getStatus();
    }
}
