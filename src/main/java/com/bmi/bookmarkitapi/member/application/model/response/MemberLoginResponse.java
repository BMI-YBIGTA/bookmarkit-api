package com.bmi.bookmarkitapi.member.application.model.response;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
    private final Long id;
    private final String email;
    private final String name;
    private final String token;

    public MemberLoginResponse(Member member, String token) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        this.token = token;
    }
}
