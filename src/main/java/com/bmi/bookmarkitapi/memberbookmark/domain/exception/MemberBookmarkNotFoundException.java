package com.bmi.bookmarkitapi.memberbookmark.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class MemberBookmarkNotFoundException extends NotFoundException{
    public MemberBookmarkNotFoundException() {
        super("유저 북마크가 존재하지 않습니다.");
    }
}
