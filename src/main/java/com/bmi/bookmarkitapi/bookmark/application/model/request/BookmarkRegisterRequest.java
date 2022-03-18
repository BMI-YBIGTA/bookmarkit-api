package com.bmi.bookmarkitapi.bookmark.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkRegisterRequest {
    public String link;
}
