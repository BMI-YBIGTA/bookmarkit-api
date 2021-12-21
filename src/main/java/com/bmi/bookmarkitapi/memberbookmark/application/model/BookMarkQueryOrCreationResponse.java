package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkQueryOrCreationResponse {
    public Long bookmarkId;

    public BookMarkQueryOrCreationResponse(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
