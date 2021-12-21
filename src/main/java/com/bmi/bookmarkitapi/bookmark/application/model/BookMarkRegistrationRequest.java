package com.bmi.bookmarkitapi.bookmark.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMarkRegistrationRequest {
    public String header;
    public String link;
    public String content;
}
