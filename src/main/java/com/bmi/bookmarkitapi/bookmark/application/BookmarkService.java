package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;

public interface BookmarkService {

    Bookmark register(BookmarkRegisterRequest request);
}
