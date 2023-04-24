package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IValidationHandler;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ValidationAdapter implements IValidationHandler {
    @Override
    public void validate(UserRequestDto userRequestDto) {
        if (userRequestDto == null)
            throw new RequestException("El usuario no puede ser nulo", HttpStatus.BAD_REQUEST);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        if (!violations.isEmpty()) {
            ConstraintViolation<UserRequestDto> violation = violations.iterator().next();
            throw new RequestException(violation.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
