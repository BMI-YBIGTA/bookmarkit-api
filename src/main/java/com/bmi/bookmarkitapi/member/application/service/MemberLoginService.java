package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.member.application.model.LoginDto;
import com.bmi.bookmarkitapi.member.application.model.MemberDto;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberLoginService {

    private final MemberQueryService memberQueryService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginDto.Response login(LoginDto.Request loginDto) {
        Member member = memberQueryService.query(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못 입력되었습니다.");
        }
        String token = jwtTokenProvider.issueToken(member.getId(), member.getUsername());

        return LoginDto.Response.from(member, token);
    }
}
