package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoletas.application.handler.IEmployeeHandler;
import com.pragma.plazoletas.application.handler.IValidateRestaurantOwnerId;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IEmployRequestMapper;
import com.pragma.plazoletas.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantEmployeeHandler implements IEmployeeHandler {
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final IEmployRequestMapper employRequestMapper;
    private final IValidationHandler validationHandler;
    private final IValidateRestaurantOwnerId validateRestaurantOwnerId;
    private final IRestaurantServicePort restaurantServicePort;
    @Override
    public void saveEmployeeHandler(EmployeeRequestDto employeeRequestDto, String token){
        validationHandler.validate(employeeRequestDto);
        Restaurant restaurant =  restaurantServicePort
                .findByRestaurantId(employeeRequestDto.getRestaurantId());
        validateRestaurantOwnerId.validateRestaurantOwnerId(restaurant,token);
        restaurantEmployeeServicePort.saveRestaurantEmployee(employRequestMapper
                .toRestaurantEmployeeModel(employeeRequestDto));
    }

}
