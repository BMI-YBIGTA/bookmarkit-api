package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkRegistrationService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkTitleModificationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/memberbookmark")
@RestController
public class MemberBookmarkCommandController {

    private final MemberBookmarkRegistrationService registrationService;
    private final MemberBookmarkTitleModificationService titleModificationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public MemberBookmark register(
            @RequestBody MemberBookmarkRegistrationRequest request,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return registrationService.register(id, request);
    }

    @PatchMapping("{id}/title")
    public MemberBookmark titleModify(
            @PathVariable Long id,
            @RequestBody MemberBookmarkTitleModificationRequest request
    ) {
        return titleModificationService.modify(id, request);
    }
}
