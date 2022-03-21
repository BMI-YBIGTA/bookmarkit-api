package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.infrastructure.BookmarkRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberBookmarkServiceImpl implements MemberBookmarkService {

    private final MemberBookmarkQueryService memberBookmarkQueryService;
    private final MemberBookmarkCommandService memberBookmarkCommandService;
    private final BookmarkRegistrationService bookmarkRegistrationService;

    @Override
    public MemberBookmarkResponse register(Long memberId, MemberBookmarkRegisterRequest request) {
        Long bookmarkId = bookmarkRegistrationService.saveIfNotExist(request.getLink()).getId();
        return new MemberBookmarkResponse(memberBookmarkCommandService.create(memberId, bookmarkId, request.getTitle()));
    }

    @Transactional
    @Override
    public MemberBookmarkResponse modifyTitle(Long id, MemberBookmarkTitleModifyRequest request) {
        MemberBookmark memberBookmark = memberBookmarkQueryService.findById(id);
        memberBookmark.modifyTitle(request.getTitle());
        return new MemberBookmarkResponse(memberBookmark);
    }
}
