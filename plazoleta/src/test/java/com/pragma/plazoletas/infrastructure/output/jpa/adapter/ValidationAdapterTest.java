package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidationAdapterTest {
    private static IValidationHandler validationHandler;
    private RestaurantRequestDto restaurantRequestDto;


    @BeforeAll
    static void setUp(){
        validationHandler = new ValidationAdapter();
    }

    @BeforeEach
    void setUpOneTeOne(){
        restaurantRequestDto = new RestaurantRequestDto("restaurante 1", "cra 1 este # 162-46",
                "1255666", "http://img.png","1125555", 1l);
    }

    @Test
    void validateUserSuccessTest() {
        assertDoesNotThrow(() -> validationHandler.validate(restaurantRequestDto));
    }
    @Test
    void validateUserNullTest() {
        restaurantRequestDto = null;
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("El restaurante no puede ser nulo", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"123", " ", ""})
    void validateNameTestException(String name) {
        restaurantRequestDto.setName(name);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("El nombre debe contener al menos una letra", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "calle", " ","","cra1este 162-46"})
    void validateAddressTestException(String addresss) {
        restaurantRequestDto.setAddress(addresss);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("Formato de dirección incorrecto", exception.getMessage());
    }


}