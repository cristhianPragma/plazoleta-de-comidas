package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Restaurant;

public interface IRestaurantPersitentPort {
    void saveRestaurant(Restaurant restaurant);
}
