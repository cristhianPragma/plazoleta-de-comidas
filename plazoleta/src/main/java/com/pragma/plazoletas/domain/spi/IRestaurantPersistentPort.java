package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistentPort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant findByRestaurantId(Long restaurantId);
    List<Restaurant> listAllRestaurantPaged(int pageSize, int pageNumber);
}
