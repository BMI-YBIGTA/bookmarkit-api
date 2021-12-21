package com.bmi.bookmarkitapi.member.domain.exception;

public class DuplicateMemberException extends RuntimeException {

    public DuplicateMemberException() {
        super("이미 사용 중인 이메일입니다.");
    }
}
