package com.bmi.bookmarkitapi.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CommonException extends RuntimeException {
    public final String message;
}
