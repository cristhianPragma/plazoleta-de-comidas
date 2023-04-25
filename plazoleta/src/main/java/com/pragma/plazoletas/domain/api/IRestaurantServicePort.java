package com.pragma.plazoletas.domain.api;

import com.pragma.plazoletas.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant findByRestaurantId(Long restaurantId);
}
