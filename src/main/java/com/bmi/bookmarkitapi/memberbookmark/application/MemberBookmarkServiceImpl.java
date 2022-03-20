package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.infrastructure.BookmarkRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberBookmarkServiceImpl implements MemberBookmarkService {

    private final MemberBookmarkQueryService memberBookmarkQueryService;
    private final MemberBookmarkCommandService memberBookmarkCommandService;
    private final BookmarkRegistrationService bookmarkRegistrationService;

    @Override
    public List<MemberBookmarkResponse> getMemberBookmarks() {
        return memberBookmarkQueryService.findAll()
                .stream()
                .map(MemberBookmarkResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public MemberBookmarkResponse getMemberBookmark(Long id) {
        return new MemberBookmarkResponse(memberBookmarkQueryService.findById(id));
    }

    @Override
    public Page<MemberBookmarkResponse> getMemberBookmarkPage(int page, int size) {
        return memberBookmarkQueryService.findByPage(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")))
                .map(MemberBookmarkResponse::new);
    }

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
