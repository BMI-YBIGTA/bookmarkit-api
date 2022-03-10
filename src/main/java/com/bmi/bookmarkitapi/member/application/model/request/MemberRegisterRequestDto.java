package com.bmi.bookmarkitapi.member.application.model.request;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Data;

@Data
public class MemberRegisterRequestDto {
    private String email;
    private String name;
    private String password;

    public Member toEntity() {
        return new Member(email, name, password);
    }
}
