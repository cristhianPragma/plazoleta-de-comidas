package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployRequestMapperTest {
    @Autowired
    IEmployRequestMapper employRequestMapper;

    @Test
    void toRestaurantEmployeeModel() {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1L,1L);
        RestaurantEmployee restaurantEmployee = employRequestMapper
                .toRestaurantEmployeeModel(employeeRequestDto);
        assertEquals(employeeRequestDto.getRestaurantId(),restaurantEmployee.getRestaurantId());
        assertEquals(employeeRequestDto.getUserId(),restaurantEmployee.getUserId());

    }
}