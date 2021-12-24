package com.bmi.bookmarkitapi.member.application.model;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class LoginDto {

    @Data
    public static class Request {
        private String email;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String token;

        public static LoginDto.Response from(Member member, String token) {
            return new LoginDto.Response(
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    token
            );
        }
    }
}
