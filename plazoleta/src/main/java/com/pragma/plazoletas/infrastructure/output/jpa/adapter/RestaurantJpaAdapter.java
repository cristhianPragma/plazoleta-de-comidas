package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersitentPort;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersitentPort {

    private final IRestaurantRepository repository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        repository.save(restaurantEntity);
    }

}
