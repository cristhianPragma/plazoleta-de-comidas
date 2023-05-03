package com.pragma.plazoletas.infrastructure.configuration;

import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.spi.IRestaurantPersistentPort;
import com.pragma.plazoletas.domain.usecase.RestaurantUseCase;
import com.pragma.plazoletas.infrastructure.exceptionhandler.ExceptionFeignClient;
import com.pragma.plazoletas.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanEntityConfigurationRestaurant {
    private final IRestaurantRepository repository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IRestaurantPersistentPort restaurantPersistentPort(){
        return new RestaurantJpaAdapter(repository, restaurantEntityMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistentPort());
    }
    @Bean
    public ErrorDecoder errorDecoder(){
        return new ExceptionFeignClient();
    }

}
