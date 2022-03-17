package com.bmi.bookmarkitapi.member.application.model.response;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Long id;
    private final String email;
    private final String name;

    public MemberResponse(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
    }
}
