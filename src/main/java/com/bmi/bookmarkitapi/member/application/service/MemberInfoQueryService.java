package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoQueryService {

    private final MemberQueryService memberQueryService;

    public MemberDto.Response getInfo(Long id) {
        return MemberDto.Response.from(memberQueryService.query(id));
    }
}
