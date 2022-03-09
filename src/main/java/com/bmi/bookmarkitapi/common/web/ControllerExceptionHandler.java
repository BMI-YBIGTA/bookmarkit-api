package com.bmi.bookmarkitapi.common.web;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.exception.BaseException;
import com.bmi.bookmarkitapi.common.exception.DuplicateResourceException;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response.Error> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response.Error> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response.Error> handleException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response.Error> handleException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response.Error> handleException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Response.Error> handleException(DuplicateResourceException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Response.Error> handleException(IllegalStateException e) {
        return ResponseEntity.internalServerError().body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response.Error> handleException(BaseException e) {
        return ResponseEntity.internalServerError().body(new Response.Error(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response.Error> handleException(Exception e) {
        log.error("Unexpected error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(new Response.Error(e.getMessage()));
    }
}
