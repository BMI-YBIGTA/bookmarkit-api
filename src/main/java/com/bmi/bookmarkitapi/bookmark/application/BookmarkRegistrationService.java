package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.BookmarkRegistrationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkRegistrationService {
    private final BookmarkCommandService commandService;

    public Bookmark register(BookmarkRegistrationRequest request){
        return commandService.create(request.getLink());
    }
}
