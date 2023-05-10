package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;

public interface IValidationHandler {
    public <T> void validate(T requestDto);
}
