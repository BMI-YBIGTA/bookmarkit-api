package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkRegistrationService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkTitleModificationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberbookmark")
public class MemberBookMarkCommandController {
    private final MemberBookMarkRegistrationService registrationService;
    private final MemberBookMarkTitleModificationService titleModificationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public MemberBookMark register(
            @RequestBody MemberBookMarkRegistrationRequest request,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return registrationService.register(id, request);
    }

    @PatchMapping("{id}/title")
    public MemberBookMark titleModify(
            @PathVariable Long id,
            @RequestBody MemberBookMarkTitleModificationRequest request
    ) {
        return titleModificationService.modify(id, request);
    }
}
