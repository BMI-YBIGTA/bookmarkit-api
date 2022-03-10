package com.bmi.bookmarkitapi.member.application.model.response;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Getter;

@Getter
public class MemberLoginResponseDto {
    private final Long id;
    private final String email;
    private final String name;
    private final String token;

    public MemberLoginResponseDto(Member member, String token) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        this.token = token;
    }
}
