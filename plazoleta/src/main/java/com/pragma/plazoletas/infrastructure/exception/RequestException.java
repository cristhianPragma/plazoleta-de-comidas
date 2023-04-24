package com.pragma.plazoletas.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RequestException extends RuntimeException {
    private HttpStatus status;
    public RequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
