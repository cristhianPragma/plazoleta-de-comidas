package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import com.pragma.plazoletas.domain.spi.IRestaurantEmployeePersistentPort;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeAdapter implements IRestaurantEmployeePersistentPort {
    private final IEmployeeEntityMapper employeeEntityMapper;
    private final IRestaurantEmployeeRepository employeeRepository;
    @Override
    public void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee) {
        RestaurantEmployeeEntity entity = employeeEntityMapper.toEntityEmployee(restaurantEmployee);
        employeeRepository.save(entity);
    }
}
