package com.bmi.bookmarkitapi.member.domain.exception;

import com.bmi.bookmarkitapi.common.exception.CommonException;

public class DuplicateMemberException extends CommonException {

    public DuplicateMemberException() {
        super("이미 사용 중인 이메일입니다.");
    }
}
