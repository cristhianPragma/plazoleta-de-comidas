package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersitentPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @Mock
    private IRestaurantPersitentPort restaurantPersitentPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    void saveRestaurantTest() {
        Restaurant restaurant = new Restaurant(1l,"restaurante 1", "cra 1 N 162",
                "1255666", "http://img.png","1125555", 1l);

        doNothing().when(restaurantPersitentPort).saveRestaurant(restaurant);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersitentPort, times(1))
                .saveRestaurant(restaurant);
    }
}