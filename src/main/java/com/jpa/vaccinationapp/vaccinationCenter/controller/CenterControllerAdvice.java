package com.jpa.vaccinationapp.vaccinationCenter.controller;

import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CenterControllerAdvice {
    @ExceptionHandler(value = CenterException.class)
    public ResponseEntity<String> centerExceptionHandler(CenterException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}