package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkPassingRequest {
    public String header;
    public String link;
    public String content;

    public BookMarkPassingRequest(
            String header,
            String link,
            String content
    ) {
        this.header = header;
        this.link = link;
        this.content = content;
    }
}
