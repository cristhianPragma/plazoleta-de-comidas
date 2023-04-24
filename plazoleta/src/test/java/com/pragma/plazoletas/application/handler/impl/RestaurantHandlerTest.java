package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.handler.IOwnerValidation;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantHandlerTest {
    @Mock
    private IRestaurantServicePort restaurantServicePort;
    @Mock
    private IRestaurantRequestMapper restaurantRequestMapper;
    @Mock
    private IValidationHandler validationHandler;
    @Mock
    private IOwnerValidation ownerValidation;
    @InjectMocks
    private RestaurantHandler restaurantHandler;
    @Test
    void saveRestaurantHandlerTest() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("restaurante 1",
                "cra 1 N 162", "1255666", "http://img.png",
                "1125555", 1l);
        Restaurant restaurant;

        doNothing().when(validationHandler).validate(restaurantRequestDto);
        doNothing().when(ownerValidation).validation(restaurantRequestDto.getOwnerId());
        when(restaurantRequestMapper.toRestaurant(restaurantRequestDto))
                .thenReturn(restaurant = new Restaurant(1l,"restaurante 1", "cra 1 N 162",
                        "1255666", "http://img.png", "1125555", 1l));
        doNothing().when(restaurantServicePort).saveRestaurant(restaurant);

        restaurantHandler.saveRestaurant(restaurantRequestDto);

        verify(validationHandler, times(1))
                .validate(restaurantRequestDto);
        verify(ownerValidation, times(1))
                .validation(restaurantRequestDto.getOwnerId());
        verify(restaurantServicePort, times(1))
                .saveRestaurant(restaurant);
        assertEquals(restaurantRequestDto.getName(), restaurant.getName());
    }
}