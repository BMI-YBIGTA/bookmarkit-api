package com.bmi.bookmarkitapi.memberbookmark.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class MemberBookMarkNotFoundException extends NotFoundException{
    public MemberBookMarkNotFoundException() {
        super("유저 북마크가 존재하지 않습니다.");
    }
}
