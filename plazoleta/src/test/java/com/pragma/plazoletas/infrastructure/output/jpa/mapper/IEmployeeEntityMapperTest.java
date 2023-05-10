package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IEmployeeEntityMapperTest {
    @Autowired
    IEmployeeEntityMapper employeeEntityMapper;
    @Test
    void toModelEmployeeMapperTest() {
        RestaurantEmployeeEntity employeeEntity =new RestaurantEmployeeEntity(1L,1L);
        RestaurantEmployee restaurantEmployee = employeeEntityMapper.toModelEmployee(employeeEntity);
        assertEquals(employeeEntity.getRestaurantId(), restaurantEmployee.getRestaurantId());
        assertEquals(employeeEntity.getUserId(), restaurantEmployee.getUserId());
    }

    @Test
    void toEntityEmployeeMapperTest() {
        RestaurantEmployee employee =new RestaurantEmployee(1L,1L);
        RestaurantEmployeeEntity EmployeeEntity = employeeEntityMapper.toEntityEmployee(employee);
        assertEquals(employee.getRestaurantId(), EmployeeEntity.getRestaurantId());
        assertEquals(employee.getUserId(), EmployeeEntity.getUserId());
    }
}