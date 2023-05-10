package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.application.dto.response.MenuDishResponseDto;
import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IMenuDishEntityMapperTest {
    @Autowired
    private IMenuDishEntityMapper menuDishEntityMapper;

    @Test
    void toMenuDishEntityMapper() {
        MenuDish menuDish = new MenuDish(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", new Category(1, "Ensaladas", "Ensaldas"),1L, true);
        MenuDishEntity menuDishEntity = menuDishEntityMapper.toEntity(menuDish);
        assertAll(
                ()->assertEquals(menuDish.getId(), menuDishEntity.getId()),
                ()->assertEquals(menuDish.getName(), menuDishEntity.getName()),
                ()->assertEquals(menuDish.getPrice(), menuDishEntity.getPrice()),
                ()->assertEquals(menuDish.getDescription(), menuDishEntity.getDescription()),
                ()->assertEquals(menuDish.getUrlImage(), menuDishEntity.getUrlImage()),
                ()->assertEquals(menuDish.isActive(), menuDishEntity.isActive()),
                ()->assertEquals(menuDish.getCategory().getId(), menuDishEntity.getCategory().getId()),
                ()->assertEquals(menuDish.getCategory().getName(), menuDishEntity.getCategory().getName()),
                ()->assertNull(menuDishEntity.getRestaurant())
        );
    }

    @Test
    void toMenuDishModelMapper() {
        MenuDishEntity menuDishEntity = new MenuDishEntity(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg",  new CategoryMenuDishEntity(1,"Ensalda", "Verduras y hortalizas"),new RestaurantEntity(1L,"restaurante 1",
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
                ()->assertEquals(menuDishEntity.getCategory().getId(), menuDish.getCategory().getId()),
                ()->assertEquals(menuDishEntity.getRestaurant().getId(), menuDish.getId())
        );
    }

    @Test
    void toMenuDishEntityListMapperTest() {
        List<MenuDishEntity> menuDishEntities= List.of(new MenuDishEntity(1L,"Ensalada fria",
                        30000, "Ensalada con multiples verduras",
                        "http://Ensalada.jpg",
                        new CategoryMenuDishEntity(1,"Ensaldas", "Ensalada fira"),
                        new RestaurantEntity(),true),

                new MenuDishEntity(2L,"Ensalada simple",
                        30000, "Ensalada con verduras",
                        "http://Ensalada.jpg",
                        new CategoryMenuDishEntity(1,"Ensaldas", "Ensalada fira"),
                        new RestaurantEntity(),true));

        List<MenuDish>menuDishListModel =
                menuDishEntityMapper.toMenuDishModelList(menuDishEntities);

        assertAll(
                ()->assertEquals(menuDishEntities.size(), menuDishListModel.size()),
                ()->assertEquals(menuDishEntities.get(0).getId(), menuDishListModel.get(0).getId()),
                ()->assertEquals(menuDishEntities.get(0).getName(), menuDishListModel.get(0).getName()),
                ()->assertEquals(menuDishEntities.get(0).getPrice(), menuDishListModel.get(0).getPrice()),
                ()->assertEquals(menuDishEntities.get(0).getDescription(), menuDishListModel.get(0).getDescription()),
                ()->assertEquals(menuDishEntities.get(0).getUrlImage(), menuDishListModel.get(0).getUrlImage()),
                ()->assertEquals(menuDishEntities.get(0).getCategory().getId(), menuDishListModel.get(0).getCategory().getId()),
                ()->assertEquals(menuDishEntities.get(0).getCategory().getName(), menuDishListModel.get(0).getCategory().getName())
        );
    }
}