package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookmarkQueryOrCreationRequest {
    public String link;

    public BookmarkQueryOrCreationRequest(
            String link
    ) {
        this.link = link;
    }
}
