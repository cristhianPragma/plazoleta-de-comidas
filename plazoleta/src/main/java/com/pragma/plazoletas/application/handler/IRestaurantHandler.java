package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);
}
