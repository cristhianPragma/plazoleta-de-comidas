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
        restaurantRequestDto = new RestaurantRequestDto("restaurante 1", "cra1 este #162-46",
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
        assertEquals("El request enviado es nulo", exception.getMessage());
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
    @ValueSource(strings = {"123", "calle", " ",""})
    void validateAddressTestException(String address) {
        restaurantRequestDto.setAddress(address);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("Formato de dirección incorrecto", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123abc", "abc", " ", ""})
    void validateRestaurantPhoneTestException(String phoneNumber) {
        restaurantRequestDto.setRestaurantPhone(phoneNumber);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("El número teléfonico debe contener entre 6 y 13 dígitos", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {""})
    void validateUrlLogoTestException(String urlLogo) {
        restaurantRequestDto.setUrlLogo(urlLogo);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("La url del logo es requerida", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"123abc", "abc", " ", ""})
    void validateNitTestException(String nit) {
        restaurantRequestDto.setNit(nit);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("El número de NIT debe contener entre 3 y 20 dígitos", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(longs = {-5,0})
    void validateOwnerIdTestException(Long ownerId) {
        restaurantRequestDto.setOwnerId(ownerId);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(restaurantRequestDto));
        assertEquals("Id de propietario incorrecto", exception.getMessage());
    }
}