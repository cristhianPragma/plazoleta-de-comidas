package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ValidationOwnerRestaurantTest {
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private ValidationOwnerRestaurant validate;
    private Restaurant restaurant;
    private String token;
    private String jwt;
    @BeforeEach
    void setUp(){
        restaurant = new Restaurant(1L,"restaurante 1", "cra1 este #162-46",
                "1255666", "http://img.png","1125555", 1l);
        token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OCwic3ViIjoi";
        jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OCwic3ViIjoi";
    }
    @Test
    void validateRestaurantOwnerIdAdapterSuccessTest() {
        when(jwtService.parseJwt(token)).thenReturn(jwt);
        when(jwtService.isValidToken(jwt)).thenReturn(true);
        when(jwtService.extractUserId(jwt)).thenReturn(1L);
        assertDoesNotThrow(() -> validate.validateRestaurantOwnerId(restaurant, token));
    }
    @Test
    void validateRestaurantOwnerIdAdapterTokenInvalidTest() {
        when(jwtService.parseJwt(token)).thenReturn(jwt);
        when(jwtService.isValidToken(jwt)).thenReturn(false);
        RequestException exception = assertThrows(RequestException.class,
                () -> validate.validateRestaurantOwnerId(restaurant, token));
    }
    @Test
    void validateRestaurantOwnerIdAdapterExceptionTest() {
        when(jwtService.parseJwt(token)).thenReturn(jwt);
        when(jwtService.isValidToken(jwt)).thenReturn(true);
        when(jwtService.extractUserId(jwt)).thenReturn(2L);

        RequestException exception = assertThrows(RequestException.class,
                ()->validate.validateRestaurantOwnerId(restaurant, token));
        assertEquals("El restaurante no pertenece a este propietario", exception.getMessage());
    }
}