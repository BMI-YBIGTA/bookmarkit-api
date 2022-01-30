package com.bmi.bookmarkitapi.memberbookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.Data;

@Data
public class BookmarkSearchDto {
    public String mainCategory;
    public String subCategory;
    public String title;
    public String link;
    public String content;
    public BookmarkStatus status;

    public BookmarkSearchDto(String mainCategory, String subCategory, String title, String link, String content, BookmarkStatus status) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.title = title;
        this.link = link;
        this.content = content;
        this.status = status;
    }
}
