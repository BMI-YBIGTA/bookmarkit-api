package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkQueryOrCreationRequest {
    public String link;

    public BookMarkQueryOrCreationRequest(
            String link
    ) {
        this.link = link;
    }
}
