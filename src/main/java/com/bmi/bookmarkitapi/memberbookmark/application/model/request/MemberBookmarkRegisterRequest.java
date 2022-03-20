package com.bmi.bookmarkitapi.memberbookmark.application.model.request;

import lombok.Data;

@Data
public class MemberBookmarkRegisterRequest {
    private String title;
    private String link;
}
