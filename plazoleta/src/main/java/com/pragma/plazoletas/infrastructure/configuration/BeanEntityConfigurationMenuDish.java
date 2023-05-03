package com.pragma.plazoletas.infrastructure.configuration;

import com.pragma.plazoletas.domain.api.IMenuDishServicePort;
import com.pragma.plazoletas.domain.spi.IMenuDishPersistentPort;;
import com.pragma.plazoletas.domain.usecase.MenuDishUseCase;
import com.pragma.plazoletas.infrastructure.output.jpa.adapter.MenuDishJpaAdapter;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IMenuDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishCategoryRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanEntityConfigurationMenuDish {
    private final IMenuDishRepository menuDishRepository;
    private final IMenuDishEntityMapper menuDishEntityMapper;
    private final IMenuDishCategoryRepository menuDishCategoryRepository;
    private final IRestaurantRepository repository;

    @Bean
    public IMenuDishPersistentPort menuDishPersistentPort(){
        return new MenuDishJpaAdapter(menuDishRepository,menuDishEntityMapper,
                menuDishCategoryRepository,repository);
    }
    @Bean
    public IMenuDishServicePort menuDishServicePort(){
        return new MenuDishUseCase(menuDishPersistentPort());
    }

}
