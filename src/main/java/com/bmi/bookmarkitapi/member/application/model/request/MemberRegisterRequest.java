package com.bmi.bookmarkitapi.member.application.model.request;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberRegisterRequest {
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public Member toEntity() {
        return new Member(email, name, password);
    }
}
