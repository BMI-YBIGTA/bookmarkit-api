package com.bmi.bookmarkitapi.memberbookmark.application.model.response;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarkQueryResponse {
    private Long memberBookmarkId;
    private String mainCategory;
    private String subCategory;
    private String title;
    private String link;
    private String createdDate;
    private BookmarkStatus status;
}
