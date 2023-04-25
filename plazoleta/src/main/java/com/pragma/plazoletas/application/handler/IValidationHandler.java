package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;

public interface IValidationHandler {
    public <T> void validate(T requestDto);
}
