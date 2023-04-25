package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.domain.model.Restaurant;

public interface IValidateRestaurantOwnerId {
    void validateRestaurantOwnerId(Restaurant restaurant, Long ownerId);
}
