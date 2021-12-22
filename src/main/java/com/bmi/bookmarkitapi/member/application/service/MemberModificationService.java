package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberModificationService {

    private final MemberQueryService memberQueryService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto.Response modifyInfo(Long id, MemberDto.Request.ModifyInfo memberDto) {
        Member member = memberQueryService.query(id);

        member.modifyInfo(memberDto.getEmail(), memberDto.getName(), passwordEncoder.encode(memberDto.getPassword()));

        return MemberDto.Response.from(member);
    }
}
