package com.bmi.bookmarkitapi.common.exception;

public abstract class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
