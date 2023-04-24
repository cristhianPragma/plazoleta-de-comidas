package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.handler.IOwnerValidation;
import com.pragma.plazoletas.application.handler.IRestaurantHandler;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IValidationHandler validationHandler;
    private final IOwnerValidation ownerValidation;
    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        validationHandler.validate(restaurantRequestDto);
        ownerValidation.validation(restaurantRequestDto.getOwnerId());
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant);
    }
}
