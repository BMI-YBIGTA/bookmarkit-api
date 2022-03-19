package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.common.exception.DuplicateResourceException;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequest;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponse;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponse;
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
    public MemberResponse getInfo(Long id) {
        return new MemberResponse(memberQueryService.findById(id));
    }

    @Transactional
    @Override
    public MemberResponse modifyInfo(Long id, MemberModifyInfoRequest request) {
        Member member = memberQueryService.findById(id);
        member.modifyInfo(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
        return new MemberResponse(member);
    }

    @Override
    public MemberResponse register(MemberRegisterRequest request) {
        memberQueryService.findByEmail(request.getEmail())
                .ifPresent(value -> {
                    throw new DuplicateResourceException("이미 가입된 이메일입니다. email=" + request.getEmail());
                });

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Member member = memberCommandService.save(request.toEntity());
        return new MemberResponse(member);
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberQueryService.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다. email=" + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다");
        }

        String token = jwtTokenProvider.issueToken(member.getId(), member.getUsername());
        return new MemberLoginResponse(member, token);
    }
}
