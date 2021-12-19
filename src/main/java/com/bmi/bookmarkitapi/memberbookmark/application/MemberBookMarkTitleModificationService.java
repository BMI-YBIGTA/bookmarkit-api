package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBookMarkTitleModificationService {
    private final MemberBookMarkQueryService queryService;
    private final MemberBookMarkCommandService commandService;

    public MemberBookmark modify(
            Long id,
            MemberBookMarkTitleModificationRequest request
    ) {
        MemberBookmark memberBookmark = queryService.query(id);
        memberBookmark.titleModify(request.title);
        return commandService.save(memberBookmark);
    }
}
