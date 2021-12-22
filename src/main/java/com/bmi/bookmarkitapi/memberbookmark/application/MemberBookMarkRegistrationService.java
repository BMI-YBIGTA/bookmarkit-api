package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryOrCreationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBookMarkRegistrationService {
    private final MemberBookMarkCommandService commandService;
    private final IBookMarkQueryOrCreationService bookMarkQueryOrCreationService;

    public MemberBookMark register(
            MemberBookMarkRegistrationRequest request
    ) {
        Long bookMarkId = bookMarkQueryOrCreationService.queryOrCreate(
                new BookMarkQueryOrCreationRequest(
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
