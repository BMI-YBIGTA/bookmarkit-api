package com.bmi.bookmarkitapi.common.exception;

import com.bmi.bookmarkitapi.common.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Response.Error defaultExceptionHandler(RuntimeException e) {
        return new Response.Error(e.getMessage());
    }

    @ExceptionHandler(CommonException.class)
    public Response.Error commonExceptionHandler(CommonException e) {
        return new Response.Error(e.message);
    }
}