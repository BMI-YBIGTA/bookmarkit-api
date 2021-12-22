package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkRegistrationService;
import com.bmi.bookmarkitapi.bookmark.application.model.BookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.exception.BookMarkNotFoundException;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookMarkQueryOrCreationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryOrCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkQueryOrCreationService implements IBookMarkQueryOrCreationService {
    private final BookMarkQueryService bookMarkQueryService;
    private final BookMarkRegistrationService bookMarkRegistrationService;

    @Override
    public BookMarkQueryOrCreationResponse queryOrCreate(BookMarkQueryOrCreationRequest request) {
        try {
            BookMark foundBookmark = bookMarkQueryService.query(request.link);
            return new BookMarkQueryOrCreationResponse(
                    foundBookmark.getId()
            );
        } catch (BookMarkNotFoundException exception) {
            BookMark createdBookmark = bookMarkRegistrationService.register(
                    new BookMarkRegistrationRequest(
                        request.header,
                        request.link,
                        request.content
                    )
            );
            return new BookMarkQueryOrCreationResponse(
                    createdBookmark.getId()
            );
        }
    }
}
