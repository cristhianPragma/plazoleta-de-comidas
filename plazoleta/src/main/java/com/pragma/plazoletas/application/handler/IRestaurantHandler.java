package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token);
    List<RestaurantResponseDto> restauranListResponseDtos(int pageSize, int pageNumber);
}
