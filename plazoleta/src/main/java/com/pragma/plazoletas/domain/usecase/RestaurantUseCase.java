package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersitentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersitentPort restaurantPersitentPort;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantPersitentPort.saveRestaurant(restaurant);
    }
}
