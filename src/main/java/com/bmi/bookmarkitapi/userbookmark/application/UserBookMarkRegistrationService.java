package com.bmi.bookmarkitapi.userbookmark.application;

import com.bmi.bookmarkitapi.userbookmark.application.model.UserBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import com.bmi.bookmarkitapi.userbookmark.domain.service.UserBookMarkCommandService;
import com.bmi.bookmarkitapi.userbookmark.domain.service.UserBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBookMarkRegistrationService {
    private final UserBookMarkCommandService commandService;

    public UserBookmark register(
            UserBookMarkRegistrationRequest request
    ) {
        return commandService.save(
                new UserBookmark(
                        request.userId,
                        request.bookMarkId,
                        request.title
                )
        );
    }
}
