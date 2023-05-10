package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.RestaurantEmployee;

public interface IRestaurantEmployeePersistentPort {
    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee);
}
