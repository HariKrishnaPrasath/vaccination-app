package com.jpa.vaccinationapp.admin;

import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value =AdminException.class)
    public ResponseEntity<String> adminExceptionHandler(AdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
