package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistentPort restaurantPersistentPort;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantPersistentPort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant findByRestaurantId(Long restaurantId) {
        return restaurantPersistentPort.findByRestaurantId(restaurantId);
    }
}
