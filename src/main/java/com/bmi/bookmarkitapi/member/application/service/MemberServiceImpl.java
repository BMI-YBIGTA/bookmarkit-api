package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.common.exception.DuplicateResourceException;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequestDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponseDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponseDto;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.service.MemberCommandService;
import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDto getInfo(Long id) {
        return new MemberResponseDto(memberQueryService.findById(id));
    }

    @Transactional
    @Override
    public MemberResponseDto modifyInfo(Long id, MemberModifyInfoRequestDto request) {
        Member member = memberQueryService.findById(id);
        member.modifyInfo(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
        return new MemberResponseDto(member);
    }

    @Override
    public MemberResponseDto register(MemberRegisterRequestDto request) {
        memberQueryService.findByEmail(request.getEmail())
                .ifPresent(value -> { throw new DuplicateResourceException(); });

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Member member = memberCommandService.save(request.toEntity());
        return new MemberResponseDto(member);
    }

    @Override
    public MemberLoginResponseDto login(MemberLoginRequestDto loginDto) {
        Member member = memberQueryService.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다"));

        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다");
        }

        String token = jwtTokenProvider.issueToken(member.getId(), member.getUsername());
        return new MemberLoginResponseDto(member, token);
    }
}
