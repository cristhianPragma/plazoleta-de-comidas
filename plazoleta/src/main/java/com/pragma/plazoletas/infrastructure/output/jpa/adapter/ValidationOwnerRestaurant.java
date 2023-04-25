package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.handler.IValidateRestaurantOwnerId;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationOwnerRestaurant implements IValidateRestaurantOwnerId {
    @Override
    public void validateRestaurantOwnerId(Restaurant restaurant, Long ownerId) {
        if (restaurant.getOwnerId() != ownerId)
            throw new RequestException("El restaurante no pertenece a este propietario",
                    HttpStatus.BAD_REQUEST);
    }
}
