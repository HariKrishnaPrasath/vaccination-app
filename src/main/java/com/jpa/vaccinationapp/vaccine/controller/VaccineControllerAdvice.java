package com.jpa.vaccinationapp.vaccine.controller;

import com.jpa.vaccinationapp.vaccine.VaccineException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VaccineControllerAdvice {
    @ExceptionHandler(value= VaccineException.class)
    public ResponseEntity<String> vaccineExceptionHandler(VaccineException a){
        return new ResponseEntity<>(a.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
