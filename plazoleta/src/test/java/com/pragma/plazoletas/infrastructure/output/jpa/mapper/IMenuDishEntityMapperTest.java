package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IMenuDishEntityMapperTest {
    @Autowired
    private IMenuDishEntityMapper menuDishEntityMapper;

    @Test
    void toMenuDishEntityMapper() {
        MenuDish menuDish = new MenuDish(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L, true);
        MenuDishEntity menuDishEntity = menuDishEntityMapper.toEntity(menuDish);
        assertAll(
                ()->assertEquals(menuDish.getId(), menuDishEntity.getId()),
                ()->assertEquals(menuDish.getName(), menuDishEntity.getName()),
                ()->assertEquals(menuDish.getPrice(), menuDishEntity.getPrice()),
                ()->assertEquals(menuDish.getDescription(), menuDishEntity.getDescription()),
                ()->assertEquals(menuDish.getUrlImage(), menuDishEntity.getUrlImage()),
                ()->assertEquals(menuDish.isActive(), menuDishEntity.isActive()),
                ()->assertNull(menuDishEntity.getCategory()),
                ()->assertNull(menuDishEntity.getRestaurant())
        );
    }

    @Test
    void toMenuDishModelMapper() {
        MenuDishEntity menuDishEntity = new MenuDishEntity(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg",  new CategoryMenuDishEntity(1,"Ensalda",
                "Verduras y hortalizas"),new RestaurantEntity(1L,"restaurante 1",
                "cra 1 N 162", "1255666", "http://img.png",
                "1125555", 2L), true);

        MenuDish menuDish = menuDishEntityMapper.toMenuDishModel(menuDishEntity);
        assertAll(
                ()->assertEquals(menuDishEntity.getId(), menuDish.getId()),
                ()->assertEquals(menuDishEntity.getName(), menuDish.getName()),
                ()->assertEquals(menuDishEntity.getPrice(), menuDish.getPrice()),
                ()->assertEquals(menuDishEntity.getDescription(), menuDish.getDescription()),
                ()->assertEquals(menuDishEntity.getUrlImage(), menuDish.getUrlImage()),
                ()->assertEquals(menuDishEntity.isActive(), menuDish.isActive()),
                ()->assertEquals(menuDishEntity.getCategory().getId(), menuDish.getCategoryId()),
                ()->assertEquals(menuDishEntity.getRestaurant().getId(), menuDish.getId())
        );
    }
}