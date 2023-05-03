package com.pragma.plazoletas.domain.api;

import com.pragma.plazoletas.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant findByRestaurantId(Long restaurantId);
    List<Restaurant> listAllRestaurantPaged(int pageSize, int pageNumber);
}
