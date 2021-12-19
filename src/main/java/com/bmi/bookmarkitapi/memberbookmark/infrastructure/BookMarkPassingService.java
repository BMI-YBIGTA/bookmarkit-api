package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.memberbookmark.application.IBookMarkPassingService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingResponse;
import org.springframework.stereotype.Service;

@Service
public class BookMarkPassingService implements IBookMarkPassingService {

    @Override
    public BookMarkPassingResponse pass(BookMarkPassingRequest request) {
        return new BookMarkPassingResponse(1L);
    }
}
