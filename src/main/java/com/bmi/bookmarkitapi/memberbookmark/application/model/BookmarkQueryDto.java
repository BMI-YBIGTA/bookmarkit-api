package com.bmi.bookmarkitapi.memberbookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.Data;

@Data
public class BookmarkQueryDto {
    public Long memberBookmarkId;
    public String mainCategory;
    public String subCategory;
    public String title;
    public String link;
    public String createdDate;
    public BookmarkStatus status;

    public BookmarkQueryDto(
            Long memberBookmarkId,
            String mainCategory,
            String subCategory,
            String title,
            String link,
            String createdDate,
            BookmarkStatus status
    ) {
        this.memberBookmarkId = memberBookmarkId;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.title = title;
        this.link = link;
        this.createdDate = createdDate;
        this.status = status;
    }
}
