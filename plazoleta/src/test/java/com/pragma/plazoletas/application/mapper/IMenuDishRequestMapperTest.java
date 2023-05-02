package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.domain.model.MenuDish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = {"classpath:testapplication.yml"})
class IMenuDishRequestMapperTest {
    @Autowired
    private IMenuDishRequestMapper menuDishRequestMapper;
    @Test
    void toMenuDishMapperTest() {
        MenuDishRequestDto menuDishRequestDto = new MenuDishRequestDto("Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                  "http://Ensalada.jpg", 1,1L);

        MenuDish menuDish = menuDishRequestMapper.toMenuDish(menuDishRequestDto);
        assertAll(
                ()->assertEquals(menuDishRequestDto.getName(), menuDish.getName()),
                ()->assertEquals(menuDishRequestDto.getPrice(), menuDish.getPrice()),
                ()->assertEquals(menuDishRequestDto.getDescription(), menuDish.getDescription()),
                ()->assertEquals(menuDishRequestDto.getUrlImage(), menuDish.getUrlImage()),
                ()->assertEquals(menuDishRequestDto.getCategoryId(), menuDish.getCategoryId()),
                ()->assertEquals(menuDishRequestDto.getRestaurantId(), menuDish.getRestaurantId())

        );
    }
}