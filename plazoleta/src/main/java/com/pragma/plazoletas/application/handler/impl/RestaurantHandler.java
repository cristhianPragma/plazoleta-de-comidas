package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.dto.response.RestauranListResponseDto;
import com.pragma.plazoletas.application.handler.IOwnerValidation;
import com.pragma.plazoletas.application.handler.IRestaurantHandler;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IValidationHandler validationHandler;
    private final IOwnerValidation ownerValidation;
    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token) {
        validationHandler.validate(restaurantRequestDto);
        ownerValidation.validation(restaurantRequestDto.getOwnerId(), token);
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant);
    }

    public List<RestauranListResponseDto> restauranListResponseDtos(int pageSize, int pageNumber){
         return restaurantRequestMapper
                 .toListRestauranDto(restaurantServicePort.listAllRestaurantPaged(pageSize, pageNumber));
    }
}
