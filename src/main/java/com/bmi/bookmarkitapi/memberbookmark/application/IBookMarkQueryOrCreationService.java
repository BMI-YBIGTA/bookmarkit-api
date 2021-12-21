package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryOrCreationResponse;

public interface IBookMarkQueryOrCreationService {
    BookMarkQueryOrCreationResponse queryOrCreate(BookMarkQueryOrCreationRequest request);
}
