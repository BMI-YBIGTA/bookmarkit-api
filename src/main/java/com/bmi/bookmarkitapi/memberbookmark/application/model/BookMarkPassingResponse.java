package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkPassingResponse {
    public Long bookmarkId;

    public BookMarkPassingResponse(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
