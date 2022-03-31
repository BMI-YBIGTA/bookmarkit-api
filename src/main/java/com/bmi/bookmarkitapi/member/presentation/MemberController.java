package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequest;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponse;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponse;
import com.bmi.bookmarkitapi.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Response.Item<MemberResponse> getInfo(@PathVariable Long id) {
        return new Response.Item<>(memberService.getInfo(id));
    }

    @PutMapping("/{id}")
    public Response.Item<MemberResponse> modifyInfo(
            @PathVariable Long id,
            @Valid @RequestBody MemberModifyInfoRequest request
    ) {
        return new Response.Item<>(memberService.modifyInfo(id, request));
    }

    @PostMapping("/sign-up")
    public Response.Item<MemberResponse> signUp(@Valid @RequestBody MemberRegisterRequest request) {
        return new Response.Item<>(memberService.register(request));
    }

    @PostMapping("/sign-in")
    public Response.Item<MemberLoginResponse> signIn(@Valid @RequestBody MemberLoginRequest request) {
        return new Response.Item<>(memberService.login(request));
    }
}
