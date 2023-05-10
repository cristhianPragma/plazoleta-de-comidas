package com.pragma.plazoletas.infrastructure.configuration;

import com.pragma.plazoletas.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.plazoletas.domain.spi.IRestaurantEmployeePersistentPort;
import com.pragma.plazoletas.domain.usecase.RestaurantEmployeeUseCase;
import com.pragma.plazoletas.infrastructure.output.jpa.adapter.RestaurantEmployeeAdapter;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanEntityConfigurationEmployee {
    private final IRestaurantEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Bean
    public IRestaurantEmployeePersistentPort restaurantEmployeePersistentPort(){
        return new RestaurantEmployeeAdapter(employeeEntityMapper, employeeRepository);
    }
    @Bean
    public IRestaurantEmployeeServicePort restaurantEmployeeServicePort(){
        return new RestaurantEmployeeUseCase(restaurantEmployeePersistentPort());
    }

}
