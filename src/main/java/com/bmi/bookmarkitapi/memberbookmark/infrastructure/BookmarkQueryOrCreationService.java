package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookmarkService;
import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookmarkQueryOrCreationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkQueryOrCreationService implements IBookmarkQueryOrCreationService {

    private final BookmarkQueryService bookmarkQueryService;
    private final BookmarkService bookmarkService;

    @Override
    public BookmarkQueryOrCreationResponse queryOrCreate(BookmarkQueryOrCreationRequest request) {
        try {
            Bookmark foundBookmark = bookmarkQueryService.findByLink(request.link);
            return new BookmarkQueryOrCreationResponse(foundBookmark.getId());
        } catch (ResourceNotFoundException exception) {
            Bookmark createdBookmark = bookmarkService.register(
                    new BookmarkRegisterRequest(request.link)
            );

            return new BookmarkQueryOrCreationResponse(createdBookmark.getId());
        }
    }
}
