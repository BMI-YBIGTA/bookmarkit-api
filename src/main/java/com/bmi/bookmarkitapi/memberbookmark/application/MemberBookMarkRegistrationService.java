package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBookMarkRegistrationService {
    private final MemberBookMarkCommandService commandService;
    private final IBookMarkPassingService bookMarkPassingService;

    public MemberBookmark register(
            MemberBookMarkRegistrationRequest request
    ) {
        Long bookMarkId = bookMarkPassingService.pass(
                new BookMarkPassingRequest(
                        request.header,
                        request.link,
                        request.content
                )
        ).bookmarkId;
        return commandService.create(
                request.memberId,
                bookMarkId,
                request.title
        );
    }
}
