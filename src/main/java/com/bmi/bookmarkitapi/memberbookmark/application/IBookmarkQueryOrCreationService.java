package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationResponse;

public interface IBookmarkQueryOrCreationService {
    BookmarkQueryOrCreationResponse queryOrCreate(BookmarkQueryOrCreationRequest request);
}
