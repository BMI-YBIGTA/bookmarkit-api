package com.bmi.bookmarkitapi.member.application.model;

import com.bmi.bookmarkitapi.member.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class MemberDto {

    public static class Request {

        @Data
        public static class Register {
            private String email;
            private String name;
            private String password;

            public Member toEntity() {
                return new Member(email, name, password);
            }
        }

        @Data
        public static class Login {
            private String email;
            private String password;
        }

        @Data
        public static class ModifyInfo {
            private String email;
            private String name;
            private String password;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String password;

        public static Response from(Member member) {
            return new Response(
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    member.getPassword()
            );
        }
    }
}
