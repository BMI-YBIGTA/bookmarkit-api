package com.bmi.bookmarkitapi.userbookmark.application.model;

import lombok.Data;

@Data
public class UserBookMarkRegistrationRequest {
    public Long userId;
    public Long bookMarkId;
    public String title;
}
