package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class MemberBookMarkRegistrationRequest {
    public String title;
    public String header;
    public String link;
    public String content;
}
