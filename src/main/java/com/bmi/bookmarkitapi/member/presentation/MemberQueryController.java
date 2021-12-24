package com.bmi.bookmarkitapi.member.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.application.service.MemberInfoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberQueryController {

    private final MemberInfoQueryService memberInfoQueryService;

    @GetMapping("/member/{id}")
    public Response.Item<MemberDto.Response> getInfo(@PathVariable Long id) {
        return new Response.Item<>(memberInfoQueryService.getInfo(id));
    }
}
