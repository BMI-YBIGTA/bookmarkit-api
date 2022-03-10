package com.bmi.bookmarkitapi.member.application.model.response;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String email;
    private final String name;

    public MemberResponseDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
    }
}
