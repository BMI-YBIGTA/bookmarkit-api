package com.bmi.bookmarkitapi.common.exception;

public abstract class NotFoundException extends CommonException {

    public NotFoundException(String message) {
        super(message);
    }
}
