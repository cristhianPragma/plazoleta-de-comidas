package com.pragma.plazoletas.infrastructure.exceptionhandler;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler({RequestException.class})
    public ResponseEntity<Map<String, String>> handlerNoDataFoundException(
            RequestException requestException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(requestException.getMessage());
        return ResponseEntity.status(requestException.getStatus())
                .body(Collections.singletonMap(MESSAGE, exceptionResponse.getMessage()));
    }
}