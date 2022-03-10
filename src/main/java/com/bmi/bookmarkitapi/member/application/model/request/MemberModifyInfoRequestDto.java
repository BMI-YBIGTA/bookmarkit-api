package com.bmi.bookmarkitapi.member.application.model.request;

import lombok.Data;

@Data
public class MemberModifyInfoRequestDto {
    private String email;
    private String name;
    private String password;
}
