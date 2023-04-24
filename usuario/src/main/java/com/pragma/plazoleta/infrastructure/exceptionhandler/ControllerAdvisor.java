package com.pragma.plazoleta.infrastructure.exceptionhandler;

import com.pragma.plazoleta.infrastructure.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler({RequestException.class})
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            RequestException requestException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(requestException.getMessage());
        return ResponseEntity.status(requestException.getStatus())
                .body(Collections.singletonMap(MESSAGE, exceptionResponse.getMessage()));
    }

}