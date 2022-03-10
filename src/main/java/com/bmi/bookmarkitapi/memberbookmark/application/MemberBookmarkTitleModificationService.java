package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberBookmarkTitleModificationService {

    private final MemberBookmarkQueryService queryService;
    private final MemberBookmarkCommandService commandService;

    public MemberBookmark modify(Long id, MemberBookmarkTitleModificationRequest request) {
        MemberBookmark memberBookmark = queryService.findById(id);

        memberBookmark.titleModify(request.title);

        return commandService.save(memberBookmark);
    }
}
