package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantJpaAdapterTest {
    @Mock
    private IRestaurantRepository repository;
    @Mock
    private IRestaurantEntityMapper restaurantEntityMapper;
    @InjectMocks
    RestaurantJpaAdapter restaurantJpaAdapter;

    @Test
    void saveRestaurantSuccessAdapterTest() {
        RestaurantEntity restaurantEntity;
        Restaurant restaurant = new Restaurant(1l,"restaurante 1", "cra 1 N 162",
                "1255666", "http://img.png","1125555", 1l);

        when(restaurantEntityMapper.toEntity(restaurant)).thenReturn(
                restaurantEntity = new RestaurantEntity(null,"restaurante 1",
                        "cra 1 N 162", "1255666",
                        "http://img.png","1125555", 1l));
        when(repository.save(restaurantEntity)).thenReturn(restaurantEntity);

        restaurantJpaAdapter.saveRestaurant(restaurant);

        verify(restaurantEntityMapper, times(1))
                .toEntity(restaurant);
        verify(repository, times(1))
                .save(restaurantEntity);
        assertEquals("restaurante 1", restaurantEntity.getName());
    }
    @Test
    void saveRestaurantExceptionAdapterTest() {

        Restaurant existingRestaurant = new  Restaurant(1L,"restaurante 1", "cra1 este #162-46",
                "1255666", "http://img.png","1125555", 1l);

        when(repository.findByIdOrNit(existingRestaurant.getId(), existingRestaurant.getNit()))
                .thenReturn(Optional.of(new RestaurantEntity(1L,"restaurante 1",
                        "cra1 este #162-46", "1255666",
                        "http://img.png","1125555", 1l)));

        RequestException exception = assertThrows(RequestException.class,
                ()->restaurantJpaAdapter.saveRestaurant(new Restaurant(1L,"restaurante 1",
                        "cra1 este #162-46", "1255666",
                        "http://img.png","1125555", 1l)));

        assertEquals("Este restaurante ya existe", exception.getMessage());
        verify(restaurantEntityMapper, never()).toEntity(any(Restaurant.class));
        verify(repository, never()).save(any(RestaurantEntity.class));
    }

}