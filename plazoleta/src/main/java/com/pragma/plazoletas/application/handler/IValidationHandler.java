package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;

public interface IValidationHandler {
    <T> void validate(T requestDto);
}
