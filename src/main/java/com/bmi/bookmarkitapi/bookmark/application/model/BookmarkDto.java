package com.bmi.bookmarkitapi.bookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.Getter;

public class BookmarkDto {

    @Getter
    public static class Response {
        private final String link;
        private final String content;
        private final String mainCategory;
        private final String subCategory;
        private final BookmarkStatus status;

        public Response(Bookmark bookmark) {
            link = bookmark.getLink();
            content = bookmark.getContent();
            mainCategory = bookmark.getMainCategory();
            subCategory = bookmark.getSubCategory();
            status = bookmark.getStatus();
        }
    }
}
