package com.pragma.plazoletas.infrastructure.configuration;

import com.pragma.plazoletas.domain.api.IAssignOrderServicePort;
import com.pragma.plazoletas.domain.spi.IAssignOrderPersistentPort;
import com.pragma.plazoletas.domain.usecase.AssignedOrderUseCase;
import com.pragma.plazoletas.infrastructure.output.jpa.adapter.AssignedOrderAdapter;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BeanConfigurationAssignedOrder {
    private final IOrderEntityRepository orderEntityRepository;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;

    @Bean
    public IAssignOrderPersistentPort persistentPort(){
        return new AssignedOrderAdapter(orderEntityRepository, restaurantEmployeeRepository);
    }
    @Bean
    public IAssignOrderServicePort servicePort(){
        return new AssignedOrderUseCase(persistentPort());
    }
}
