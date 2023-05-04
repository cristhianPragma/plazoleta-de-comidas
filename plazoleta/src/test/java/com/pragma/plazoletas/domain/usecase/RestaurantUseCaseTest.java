package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersistentPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @Mock
    private IRestaurantPersistentPort restaurantPersistentPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    void saveRestaurantUseCaseTest() {
        Restaurant restaurant = new Restaurant(1l,"restaurante 1", "cra 1 N 162",
                "1255666", "http://img.png","1125555", 1l);

        doNothing().when(restaurantPersistentPort).saveRestaurant(restaurant);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistentPort, times(1))
                .saveRestaurant(restaurant);
    }
    @Test
    void listAllRestaurantPagedUseCaseTest() {
        int pageSize =2, pageNumber=1;
        when(restaurantPersistentPort.listAllRestaurantPaged(pageSize, pageNumber))
                .thenReturn(List.of(new Restaurant(),new Restaurant()));

        List<Restaurant>restaurants = restaurantUseCase.listAllRestaurantPaged(pageSize, pageNumber);

        verify(restaurantPersistentPort, times(1))
                .listAllRestaurantPaged(pageSize, pageNumber);
        assertEquals(2, restaurants.size());
    }
}