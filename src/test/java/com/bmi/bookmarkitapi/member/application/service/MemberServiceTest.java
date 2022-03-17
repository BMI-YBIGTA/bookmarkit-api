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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberQueryService memberQueryService;
    @Mock
    MemberCommandService memberCommandService;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    MemberServiceImpl memberService;

    @DisplayName("회원을 조회하면 사용자 정보가 반환된다")
    @Test
    void getInfo() {
        Long id = 1L;
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";

        Member member = new Member(email, name, password);
        when(memberQueryService.findById(id)).thenReturn(member);

        MemberResponseDto result = memberService.getInfo(id);

        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getName()).isEqualTo(name);
    }

    @DisplayName("회원 정보를 수정하면 요청된 데이터로 정보가 수정되고 사용자 정보가 반환된다")
    @Test
    void modifyInfo() {
        Long id = 1L;
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";
        String newName = "member2";

        when(passwordEncoder.encode(password)).thenReturn(password);
        Member member = new Member(email, name, passwordEncoder.encode(password));
        when(memberQueryService.findById(id)).thenReturn(member);
        MemberModifyInfoRequestDto request = new MemberModifyInfoRequestDto();
        request.setName(newName);

        MemberResponseDto result = memberService.modifyInfo(id, request);

        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getName()).isEqualTo(newName);
    }

    @DisplayName("요청된 이메일을 가진 사용자가 존재하지 않으면 회원가입이 완료되고 사용자 정보가 반환된다")
    @Test
    void register() {
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";

        when(memberQueryService.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(password);
        MemberRegisterRequestDto request = new MemberRegisterRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        when(memberCommandService.save(any(Member.class))).thenReturn(new Member(email, name, password));

        MemberResponseDto result = memberService.register(request);

        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @DisplayName("요청된 이메일을 가진 사용자가 존재하면 DuplicateResourceException 예외가 발생한다")
    @Test
    void registerFail() {
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";

        when(memberQueryService.findByEmail(email)).thenReturn(Optional.of(new Member(email, name, password)));
        MemberRegisterRequestDto request = new MemberRegisterRequestDto();
        request.setEmail(email);

        assertThrows(DuplicateResourceException.class, () -> memberService.register(request));
    }

    @DisplayName("요청된 이메일을 가진 사용자가 존재하고 비밀번호가 일치하면 로그인이 완료되고 사용자 정보와 토큰이 반환된다")
    @Test
    void login() {
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";
        String token = "token";
        Member member = new Member(email, name, password);

        MemberLoginRequestDto request = new MemberLoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        when(memberQueryService.findByEmail(email)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(request.getPassword(), member.getPassword())).thenReturn(true);
        when(jwtTokenProvider.issueToken(member.getId(), member.getEmail())).thenReturn(token);

        MemberLoginResponseDto result = memberService.login(request);

        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getToken()).isEqualTo(token);
    }

    @DisplayName("요청된 이메일을 가진 사용자가 존재하지 않으면 IllegalArgumentException 예외가 발생한다")
    @Test
    void loginFail1() {
        String email = "a@google.com";
        String password = "1234";

        MemberLoginRequestDto request = new MemberLoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        when(memberQueryService.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> memberService.login(request));
    }

    @DisplayName("잘못된 비밀번호를 입력할 경우 IllegalArgumentException 예외가 발생한다")
    @Test
    void loginFail2() {
        String email = "a@google.com";
        String name = "member1";
        String password = "1234";
        Member member = new Member(email, name, password);

        MemberLoginRequestDto request = new MemberLoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        when(memberQueryService.findByEmail(email)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(request.getPassword(), member.getPassword())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> memberService.login(request));
    }
}
