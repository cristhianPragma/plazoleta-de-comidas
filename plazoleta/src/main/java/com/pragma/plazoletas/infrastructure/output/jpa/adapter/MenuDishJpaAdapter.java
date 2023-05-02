package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.spi.IMenuDishPersistentPort;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IMenuDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishCategoryRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class MenuDishJpaAdapter implements IMenuDishPersistentPort {
    private final IMenuDishRepository menuDishRepository;
    private final IMenuDishEntityMapper menuDishEntityMapper;
    private final IMenuDishCategoryRepository menuDishCategoryRepository;
    private final IRestaurantRepository repository;
    @Override
    public void saveMenuDish(MenuDish menuDish) {
        MenuDishEntity menuDishEntity = menuDishEntityMapper.toEntity(menuDish);
        menuDishEntity.setCategory(menuDishCategoryRepository
                .findById(menuDish.getCategoryId()).orElseThrow(
                        ()->new  RequestException("Categoría no encontrada", HttpStatus.NOT_FOUND)));

        menuDishEntity.setRestaurant(repository.findById(menuDish.getRestaurantId()).orElseThrow(()->
                new  RequestException("Restaurante no encontrado", HttpStatus.NOT_FOUND)));

        menuDishRepository.save(menuDishEntity);
    }
    @Override
    public MenuDish findByIdMenuDish(Long id) {
        return menuDishEntityMapper.toMenuDishModel(menuDishRepository.findById(id).
                orElseThrow(()->new  RequestException("Plato no encontrado", HttpStatus.NOT_FOUND)));
    }

}
