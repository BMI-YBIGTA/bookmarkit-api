package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookmarkRegistrationService;
import com.bmi.bookmarkitapi.bookmark.application.model.BookmarkRegistrationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookmarkQueryOrCreationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkQueryOrCreationService implements IBookmarkQueryOrCreationService {
    private final BookmarkQueryService bookmarkQueryService;
    private final BookmarkRegistrationService bookmarkRegistrationService;

    @Override
    public BookmarkQueryOrCreationResponse queryOrCreate(BookmarkQueryOrCreationRequest request) {
        try {
            Bookmark foundBookmark = bookmarkQueryService.query(request.link);
            return new BookmarkQueryOrCreationResponse(foundBookmark.getId());
        } catch (ResourceNotFoundException exception) {
            Bookmark createdBookmark = bookmarkRegistrationService.register(
                    new BookmarkRegistrationRequest(request.link)
            );

            return new BookmarkQueryOrCreationResponse(createdBookmark.getId());
        }
    }
}
