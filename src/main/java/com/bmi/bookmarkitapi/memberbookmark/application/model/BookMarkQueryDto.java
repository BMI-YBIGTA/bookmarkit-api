package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkQueryDto {
    public Long memberBookMarkId;
    public String category;
    public String title;
    public String link;

    public BookMarkQueryDto(Long memberBookMarkId, String category, String title, String link) {
        this.memberBookMarkId = memberBookMarkId;
        this.category = category;
        this.title = title;
        this.link = link;
    }
}
