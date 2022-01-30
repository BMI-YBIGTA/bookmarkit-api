package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookmarkQueryOrCreationResponse {
    public Long bookmarkId;

    public BookmarkQueryOrCreationResponse(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
