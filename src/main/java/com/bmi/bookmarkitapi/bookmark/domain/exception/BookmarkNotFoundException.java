package com.bmi.bookmarkitapi.bookmark.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class BookmarkNotFoundException extends NotFoundException {
    public BookmarkNotFoundException() {
        super("북마크가 존재하지 않습니다.");
    }
}
