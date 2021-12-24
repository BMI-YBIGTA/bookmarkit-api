package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.application.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberLoginController {

    private final MemberLoginService memberLoginService;

    @PostMapping("/sign-in")
    public Response.Item<String> signIn(@RequestBody MemberDto.Request.Login memberDto) {
        return new Response.Item<>(memberLoginService.login(memberDto));
    }
}
