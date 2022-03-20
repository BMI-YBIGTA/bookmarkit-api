package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkRegistrationService {

    private final BookmarkQueryService bookmarkQueryService;
    private final BookmarkCommandService bookmarkCommandService;

    public Bookmark saveIfNotExist(String link) {
        try {
            return bookmarkQueryService.findByLink(link);
        } catch (ResourceNotFoundException exception) {
            return bookmarkCommandService.saveWithLink(link);
        }
    }
}
