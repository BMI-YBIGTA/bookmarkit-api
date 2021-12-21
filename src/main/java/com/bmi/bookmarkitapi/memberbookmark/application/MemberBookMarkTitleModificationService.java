package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBookMarkTitleModificationService {
    private final MemberBookMarkQueryService queryService;
    private final MemberBookMarkCommandService commandService;

    public MemberBookMark modify(
            Long id,
            MemberBookMarkTitleModificationRequest request
    ) {
        MemberBookMark memberBookMark = queryService.query(id);
        memberBookMark.titleModify(request.title);
        return commandService.save(memberBookMark);
    }
}
