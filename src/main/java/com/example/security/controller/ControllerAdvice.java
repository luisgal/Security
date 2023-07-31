package com.example.security.controller;

import com.example.security.core.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail onRecourceNotFound(ResourceNotFound resourceNotFound){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setDetail(resourceNotFound.getMessage());

        return problemDetail;
    }
}
