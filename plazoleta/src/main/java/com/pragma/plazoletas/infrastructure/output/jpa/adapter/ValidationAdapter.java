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
    public void validate(RestaurantRequestDto restaurantRequestDto) {
        if (restaurantRequestDto == null)
            throw new RequestException("El restaurante no puede ser nulo", HttpStatus.BAD_REQUEST);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantRequestDto);
        if (!violations.isEmpty()) {
            ConstraintViolation<RestaurantRequestDto> violation = violations.iterator().next();
            throw new RequestException(violation.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
