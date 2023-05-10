package com.pragma.plazoletas.infrastructure.configuration;

import com.pragma.plazoletas.domain.api.IOrderServicePort;
import com.pragma.plazoletas.domain.spi.IOrderPersistentPort;
import com.pragma.plazoletas.domain.usecase.OrderUseCase;
import com.pragma.plazoletas.infrastructure.output.jpa.adapter.OderJpaAdapter;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderEntityListMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IStateEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationOrder {
    private final IOrderDishEntityMapper orderEntityMapper;
    private final IOrderEntityRepository orderEntityRepository;
    private final IStateEntityRepository stateEntityRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEmployeeRepository employeeRepository;
    private final IOrderEntityListMapper orderEntityListMapper;
    private final IOrderDishMapper orderDishMapper;
    @Bean
    public IOrderPersistentPort orderPersistentPort(){
        return new OderJpaAdapter(orderEntityMapper, orderEntityRepository,
                stateEntityRepository, restaurantRepository, employeeRepository,
                orderEntityListMapper, orderDishMapper);
    }
    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistentPort());
    }
}
