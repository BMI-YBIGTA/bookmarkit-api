package com.bmi.bookmarkitapi.member.application.model.request;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
