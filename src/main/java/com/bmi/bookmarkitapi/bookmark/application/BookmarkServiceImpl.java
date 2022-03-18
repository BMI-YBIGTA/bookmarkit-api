package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkCommandService bookmarkCommandService;

    @Override
    public Bookmark register(BookmarkRegisterRequest request) {
        return bookmarkCommandService.saveWithLink(request.getLink());
    }
}
