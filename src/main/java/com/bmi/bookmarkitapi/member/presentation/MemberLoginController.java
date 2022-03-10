package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.LoginDto;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.application.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberLoginController {

    private final MemberLoginService memberLoginService;

    @PostMapping("/sign-in")
    public Response.Item<LoginDto.Response> signIn(@RequestBody LoginDto.Request loginDto) {
        return new Response.Item<>(memberLoginService.login(loginDto));
    }
}
