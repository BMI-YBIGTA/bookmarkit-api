package com.bmi.bookmarkitapi.bookmark.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class BookMarkNotFoundException extends NotFoundException {
    public BookMarkNotFoundException() {
        super("북마크가 존재하지 않습니다.");
    }
}
