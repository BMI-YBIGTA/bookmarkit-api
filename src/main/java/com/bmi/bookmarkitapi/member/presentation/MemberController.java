package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequestDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponseDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponseDto;
import com.bmi.bookmarkitapi.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Response.Item<MemberResponseDto> getInfo(@PathVariable Long id) {
        return new Response.Item<>(memberService.getInfo(id));
    }

    @PutMapping("/{id}")
    public Response.Item<MemberResponseDto> modifyInfo(
            @PathVariable Long id,
            @RequestBody MemberModifyInfoRequestDto request
    ) {
        return new Response.Item<>(memberService.modifyInfo(id, request));
    }

    @PostMapping("/sign-up")
    public Response.Item<MemberResponseDto> signUp(@RequestBody MemberRegisterRequestDto request) {
        return new Response.Item<>(memberService.register(request));
    }

    @PostMapping("/sign-in")
    public Response.Item<MemberLoginResponseDto> signIn(@RequestBody MemberLoginRequestDto request) {
        return new Response.Item<>(memberService.login(request));
    }
}
