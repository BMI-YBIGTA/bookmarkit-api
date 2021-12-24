package com.bmi.bookmarkitapi.memberbookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import lombok.Data;

@Data
public class BookMarkQueryDto {
    public String mainCategory;
    public String subCategory;
    public String title;
    public String link;
    public String createdDate;
    public BookMarkStatus status;

    public BookMarkQueryDto(String mainCategory, String subCategory, String title, String link, String createdDate, BookMarkStatus status) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.title = title;
        this.link = link;
        this.createdDate = createdDate;
        this.status = status;
    }

}
