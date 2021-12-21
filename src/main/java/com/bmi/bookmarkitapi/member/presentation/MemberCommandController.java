package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.application.service.MemberModificationService;
import com.bmi.bookmarkitapi.member.application.service.MemberRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberCommandController {

    private final MemberRegistrationService memberRegistrationService;
    private final MemberModificationService memberModificationService;

    @PostMapping("/sign-up")
    public Response.Item<MemberDto.Response> signUp(MemberDto.Request.Register memberDto) {
        return new Response.Item<>(memberRegistrationService.register(memberDto));
    }

    @PutMapping("/member/{id}")
    public Response.Item<MemberDto.Response> update(@PathVariable Long id, MemberDto.Request.ModifyInfo memberDto) {
        return new Response.Item<>(memberModificationService.modifyInfo(id, memberDto));
    }
}
