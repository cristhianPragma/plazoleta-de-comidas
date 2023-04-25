package com.pragma.plazoletas.infrastructure.output.jpa.adapter;
import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationAdapter implements IValidationHandler {
    @Override
    public <T> void validate(T requestDto) {
        if (requestDto == null)
            throw new RequestException("El request enviado es nulo", HttpStatus.BAD_REQUEST);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(requestDto);
        if (!violations.isEmpty()) {
            ConstraintViolation<T> violation = violations.iterator().next();
            throw new RequestException(violation.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
