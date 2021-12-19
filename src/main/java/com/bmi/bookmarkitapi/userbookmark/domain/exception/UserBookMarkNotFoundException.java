package com.bmi.bookmarkitapi.userbookmark.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class UserBookMarkNotFoundException extends NotFoundException{
    public UserBookMarkNotFoundException() {
        super("유저 북마크가 존재하지 않습니다.");
    }
}
