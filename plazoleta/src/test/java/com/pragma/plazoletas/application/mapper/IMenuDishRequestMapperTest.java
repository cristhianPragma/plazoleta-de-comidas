package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.response.MenuDishResponseDto;
import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

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
                ()->assertEquals(menuDishRequestDto.getCategoryId(), menuDish.getCategory().getId()),
                ()->assertEquals(menuDishRequestDto.getRestaurantId(), menuDish.getRestaurantId())

        );
    }
    @Test
    void toMenuDishResponseDtoMapperTest() {
        List<MenuDish> menuDish= List.of(new MenuDish(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras", "http://Ensalada.jpg",
                new Category(1,"Ensaldas","Ensaldas"), 1L,true),

                new MenuDish(2L,"Ensalada simple", 25000,
                        "Ensalada con multiples verduras", "http://Ensalada.jpg",
                        new Category(1,"Ensaldas","Ensaldas"), 1L,true));

        List<MenuDishResponseDto>dishResponseDto =
                menuDishRequestMapper.toMenuDishResponseDto(menuDish);

        assertAll(
                ()->assertEquals(menuDish.size(), dishResponseDto.size()),
                ()->assertEquals(menuDish.get(0).getName(), dishResponseDto.get(0).getName()),
                ()->assertEquals(menuDish.get(0).getDescription(), dishResponseDto.get(0).getDescription()),
                ()->assertEquals(menuDish.get(0).getUrlImage(), dishResponseDto.get(0).getUrlImage()),
                ()->assertEquals(menuDish.get(0).getPrice(), dishResponseDto.get(0).getPrice()),
                ()->assertEquals(menuDish.get(0).getCategory().getName(), dishResponseDto.get(0).getCategoryName())
        );
    }
}