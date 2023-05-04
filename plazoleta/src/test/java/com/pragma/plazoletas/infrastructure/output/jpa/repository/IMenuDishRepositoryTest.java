package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class IMenuDishRepositoryTest {
    @Autowired
    private IMenuDishRepository menuDishRepository;
    @Autowired
    private IMenuDishCategoryRepository categoryRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    private CategoryMenuDishEntity categoryMenuDish;
    private RestaurantEntity restaurant;
    @BeforeEach
    void SetUp(){
        categoryMenuDish = categoryRepository.save(new CategoryMenuDishEntity(1,"Ensalda",
                "Verduras y hortalizas", new ArrayList<>()));
        restaurant = restaurantRepository.save(new RestaurantEntity(null,"restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l));
    }
    @Test
    void saveMenuDishRepositoryTest(){
        MenuDishEntity menuDishEntity = new MenuDishEntity(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", categoryMenuDish,restaurant, true);

        MenuDishEntity menuDishSaved = menuDishRepository.save(menuDishEntity);

        assertEquals(menuDishEntity.getId(), menuDishSaved.getId());
        assertEquals(menuDishEntity.getName(), menuDishSaved.getName());
        assertEquals(menuDishEntity.getPrice(), menuDishSaved.getPrice());
    }
    @Test
    void Prueba(){

       menuDishRepository.save(new MenuDishEntity(null,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", categoryMenuDish,restaurant, true));
        menuDishRepository.save(new MenuDishEntity(null,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", categoryMenuDish,restaurant, true));
        menuDishRepository.save(new MenuDishEntity(null,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", categoryMenuDish,restaurant, true));
        
        System.out.println(menuDishRepository.findAll());
        System.out.println(categoryRepository.findAll());
    }
}