package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/memberbookmark")
@RestController
public class MemberBookmarkCommandController {

    private final MemberBookmarkService memberBookmarkService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public Response.Item<MemberBookmarkResponse> register(
            @RequestBody MemberBookmarkRegisterRequest request,
            HttpServletRequest httpServletRequest
    ) {
        Long memberId = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        return new Response.Item<>(memberBookmarkService.register(memberId, request));
    }

    @PutMapping("/{id}")
    public Response.Item<MemberBookmarkResponse> modifyTitle(
            @PathVariable Long id,
            @RequestBody MemberBookmarkTitleModifyRequest request
    ) {
        return new Response.Item<>(memberBookmarkService.modifyTitle(id, request));
    }
}
