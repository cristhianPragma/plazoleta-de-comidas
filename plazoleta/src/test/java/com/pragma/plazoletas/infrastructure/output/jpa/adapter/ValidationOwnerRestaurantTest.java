package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationOwnerRestaurantTest {
    private ValidationOwnerRestaurant validate;
    private Restaurant restaurant;
    @BeforeEach
    void setUp(){
        validate = new ValidationOwnerRestaurant();
        restaurant = new Restaurant(1L,"restaurante 1", "cra1 este #162-46",
                "1255666", "http://img.png","1125555", 1l);
    }
    @Test
    void validateRestaurantOwnerIdAdapterSuccessTest() {
        assertDoesNotThrow(() -> validate.validateRestaurantOwnerId(restaurant, 1L));
    }
    @Test
    void validateRestaurantOwnerIdAdapterExceptionTest() {
        assertDoesNotThrow(() -> validate.validateRestaurantOwnerId(restaurant, 1L));

        RequestException exception = assertThrows(RequestException.class,
                ()->validate.validateRestaurantOwnerId(restaurant, 2L));
        assertEquals("El restaurante no pertenece a este propietario", exception.getMessage());
    }
}