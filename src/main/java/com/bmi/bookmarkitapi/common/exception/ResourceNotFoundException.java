package com.bmi.bookmarkitapi.common.exception;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
