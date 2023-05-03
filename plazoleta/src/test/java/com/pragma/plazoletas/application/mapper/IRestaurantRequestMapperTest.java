package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.dto.response.RestauranListResponseDto;
import com.pragma.plazoletas.domain.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IRestaurantRequestMapperTest {
    @Autowired
    IRestaurantRequestMapper restaurantRequestMapper;
    @Test
    void toRestaurantMapperTest() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l);

        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        assertAll(
                ()->assertEquals(restaurantRequestDto.getName(), restaurant.getName()),
                ()->assertEquals(restaurantRequestDto.getAddress(), restaurant.getAddress()),
                ()->assertEquals(restaurantRequestDto.getRestaurantPhone(), restaurant.getRestaurantPhone()),
                ()->assertEquals(restaurantRequestDto.getUrlLogo(), restaurant.getUrlLogo()),
                ()->assertEquals(restaurantRequestDto.getNit(), restaurant.getNit()),
                ()->assertEquals(restaurantRequestDto.getOwnerId(), restaurant.getOwnerId())

        );
    }
    @Test
    void toRestaurantListDtoMapperTest() {
        List<Restaurant> restaurantList = List.of(new Restaurant(1L,"restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l),
                new Restaurant(2L,"restaurante 2",
                        "cra 1 N 162", "1255666",
                        "http://img.png","1125555", 1l));


        List<RestauranListResponseDto>restaurantListResponse =
                restaurantRequestMapper.toListRestauranDto(restaurantList);
        assertAll(
                ()->assertEquals(restaurantList.size(), restaurantListResponse.size()),
                ()->assertEquals(restaurantList.get(0).getName(), restaurantListResponse.get(0).getName()),
                ()->assertEquals(restaurantList.get(0).getUrlLogo(), restaurantListResponse.get(0).getUrlLogo())
        );
    }
}