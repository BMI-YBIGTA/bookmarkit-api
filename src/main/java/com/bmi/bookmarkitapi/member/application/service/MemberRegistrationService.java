package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.common.exception.DuplicateResourceException;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.service.MemberCommandService;
import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberRegistrationService {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final PasswordEncoder passwordEncoder;

    public MemberDto.Response register(MemberDto.Request.Register memberDto) {
        memberQueryService.query(memberDto.getEmail())
                .ifPresent(value -> { throw new DuplicateResourceException(); });

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = memberCommandService.save(memberDto.toEntity());

        return MemberDto.Response.from(member);
    }
}
