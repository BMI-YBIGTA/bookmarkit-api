package com.bmi.bookmarkitapi.userbookmark.application;

import com.bmi.bookmarkitapi.userbookmark.application.model.UserBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import com.bmi.bookmarkitapi.userbookmark.domain.service.UserBookMarkCommandService;
import com.bmi.bookmarkitapi.userbookmark.domain.service.UserBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBookMarkTitleModificationService {
    private final UserBookMarkQueryService queryService;
    private final UserBookMarkCommandService commandService;

    public UserBookmark modify(
            Long id,
            UserBookMarkTitleModificationRequest request
    ) {
        UserBookmark userBookmark = queryService.query(id);
        userBookmark.titleModify(request.title);
        return commandService.save(userBookmark);
    }
}
