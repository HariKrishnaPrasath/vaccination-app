package com.jpa.vaccinationapp.certificate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CertificateControllerAdvice {
    @ExceptionHandler(value = CertificateException.class)
    public ResponseEntity<String> adminExceptionHandler(CertificateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
