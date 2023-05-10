package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import com.pragma.plazoletas.domain.spi.IRestaurantEmployeePersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeUseCase implements IRestaurantEmployeeServicePort {
    private final IRestaurantEmployeePersistentPort restaurantEmployeePersistentPort;
    @Override
    public void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee) {
        restaurantEmployeePersistentPort.saveRestaurantEmployee(restaurantEmployee);
    }
}
