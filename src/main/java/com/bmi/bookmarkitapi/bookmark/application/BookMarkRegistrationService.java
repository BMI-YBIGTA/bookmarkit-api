package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.BookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkRegistrationService {
    private final BookMarkCommandService commandService;

    public BookMark register(BookMarkRegistrationRequest request){
        return commandService.create(request.getLink());
    }
}
