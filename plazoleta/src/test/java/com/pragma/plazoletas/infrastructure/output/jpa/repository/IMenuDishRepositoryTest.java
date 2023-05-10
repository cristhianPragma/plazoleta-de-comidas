package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

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
        categoryMenuDish = categoryRepository.save(new CategoryMenuDishEntity(null,"Ensalda",
                "Verduras y hortalizas"));
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

        assertEquals(menuDishEntity.getName(), menuDishSaved.getName());
        assertEquals(menuDishEntity.getPrice(), menuDishSaved.getPrice());
    }

    @Test
    void findByRestaurantListMenuDishRepositoryTest(){
        PageRequest pageRequest = PageRequest.of(0, 4);

        menuDishRepository.saveAll(listMenuDish());
        RestaurantEntity r1 = restaurantRepository.findAll().get(0);
        RestaurantEntity r2 = restaurantRepository.findAll().get(1);

        Page<MenuDishEntity> menuDishEntityList = menuDishRepository.findByRestaurantOrderByCategory(r1, pageRequest);
        Page<MenuDishEntity> menuDishEntityList2 = menuDishRepository.findByRestaurantOrderByCategory(r2, pageRequest);
        menuDishEntityList.forEach(e-> System.out.println(e.getName()+"- - "+e.getCategory().getName()));
        assertEquals(4, menuDishEntityList.getContent().size());
        assertEquals(2, menuDishEntityList2.getContent().size());
        assertEquals("Ensalada simple",menuDishEntityList.getContent().get(0).getName());
        assertEquals("Arroz con pollo",menuDishEntityList.getContent().get(1).getName());
    }
    private List<MenuDishEntity>listMenuDish(){
        RestaurantEntity restaurant2 = restaurantRepository.save(
                new RestaurantEntity(null,"restaurante 2", "cra 2 N 164",
                        "30059969", "http://img.png","2222266", 1l));

        CategoryMenuDishEntity categoryMenuDish2 = categoryRepository
                .save(new CategoryMenuDishEntity(null,"Granos", "Todo tipo de granos"));

        return List.of(
                new MenuDishEntity(null,"Ensalada fria", 30000,
                        "Ensalada con multiples verduras",
                        "http://Ensalada.jpg", categoryMenuDish2,restaurant, true),
                new MenuDishEntity(null,"Ensalada simple", 20000,
                        "Ensalada con verduras",
                        "http://Ensalada.jpg", categoryMenuDish,restaurant, true),
                new MenuDishEntity(null,"Arroz con pollo", 25000,
                        "Arroz con verdura y pollo",
                        "http://arroz.jpg", categoryMenuDish,restaurant, true),
                new MenuDishEntity(null,"Arroz Blanco", 25000,
                        "Arroz simple",
                        "http://arroz.jpg", categoryMenuDish2,restaurant, true),
                new MenuDishEntity(null,"Arroz paisa", 25000,
                        "Arroz con verdura y carne",
                        "http://arroz.jpg", categoryMenuDish,restaurant2, true),
                new MenuDishEntity(null,"Arroz con leche", 25000,
                        "Arroz con leche y pasas",
                        "http://arroz.jpg", categoryMenuDish,restaurant2, true)
        );
    }

}