package com.bmi.bookmarkitapi.common.exception;

public abstract class NotFoundException extends IllegalArgumentException {
    public NotFoundException(String message) {
        super(message);
    }
}
