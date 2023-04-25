package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Restaurant;

public interface IRestaurantPersistentPort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant findByRestaurantId(Long restaurantId);
}
