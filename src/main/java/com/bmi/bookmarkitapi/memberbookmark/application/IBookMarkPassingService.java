package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingResponse;

public interface IBookMarkPassingService {
    public BookMarkPassingResponse pass(BookMarkPassingRequest request);
}
