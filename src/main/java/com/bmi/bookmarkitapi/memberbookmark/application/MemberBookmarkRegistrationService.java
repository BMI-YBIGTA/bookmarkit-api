package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberBookmarkRegistrationService {

    private final MemberBookmarkCommandService commandService;
    private final IBookmarkQueryOrCreationService bookmarkQueryOrCreationService;

    public MemberBookmark register(Long id, MemberBookmarkRegistrationRequest request) {
        Long bookmarkId = bookmarkQueryOrCreationService.queryOrCreate(
                new BookmarkQueryOrCreationRequest(request.link)
        ).bookmarkId;

        return commandService.create(id, bookmarkId, request.title);
    }
}
