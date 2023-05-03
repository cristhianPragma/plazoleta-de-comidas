package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.application.dto.response.RestauranListResponseDto;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IRestaurantEntityMapperTest {
    @Autowired
    IRestaurantEntityMapper restaurantEntityMapper;
    private final Long RESTAURANT_ID = 1L;
    private final Long OWNER_ID = 1L;

    @Test
    void toEntityMapperTest() {
        Restaurant restaurant = new Restaurant(RESTAURANT_ID,"restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", OWNER_ID);

        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        assertAll(
                ()->assertEquals(restaurant.getId(), restaurantEntity.getId()),
                ()->assertEquals(restaurant.getName(), restaurantEntity.getName()),
                ()->assertEquals(restaurant.getAddress(), restaurantEntity.getAddress()),
                ()->assertEquals(restaurant.getRestaurantPhone(), restaurantEntity.getRestaurantPhone()),
                ()->assertEquals(restaurant.getUrlLogo(), restaurantEntity.getUrlLogo()),
                ()->assertEquals(restaurant.getNit(), restaurantEntity.getNit()),
                ()->assertEquals(restaurant.getOwnerId(), restaurantEntity.getOwnerId())

        );
    }

    @Test
    void toRestaurantModelMapperTest() {
        RestaurantEntity restaurantEntity = new RestaurantEntity(RESTAURANT_ID,"restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", OWNER_ID);

        Restaurant restaurant = restaurantEntityMapper.toRestaurantModel(restaurantEntity);
        assertAll(
                ()->assertEquals(restaurant.getId(), restaurantEntity.getId()),
                ()->assertEquals(restaurant.getName(), restaurantEntity.getName()),
                ()->assertEquals(restaurant.getAddress(), restaurantEntity.getAddress()),
                ()->assertEquals(restaurant.getRestaurantPhone(), restaurantEntity.getRestaurantPhone()),
                ()->assertEquals(restaurant.getUrlLogo(), restaurantEntity.getUrlLogo()),
                ()->assertEquals(restaurant.getNit(), restaurantEntity.getNit()),
                ()->assertEquals(restaurant.getOwnerId(), restaurantEntity.getOwnerId())

        );
    }
    @Test
    void toRestaurantListMapperTest() {
        List<RestaurantEntity> restaurantEntityList = List.of(
                new RestaurantEntity(1L,"restaurante 1",
                        "cra 1 N 162", "1255666",
                        "http://img.png","1125555", 1l),
                new RestaurantEntity(2L,"restaurante 2",
                        "cra 1 N 162", "1255666",
                        "http://img.png","1125555", 1l));

        List<Restaurant>restaurantList = restaurantEntityMapper.toRestautantList(restaurantEntityList);

        assertAll(
                ()->assertEquals(restaurantEntityList.size(), restaurantList.size()),
                ()->assertEquals(restaurantEntityList.get(0).getId(), restaurantList.get(0).getId()),
                ()->assertEquals(restaurantEntityList.get(0).getName(), restaurantList.get(0).getName()),
                ()->assertEquals(restaurantEntityList.get(0).getAddress(), restaurantList.get(0).getAddress()),
                ()->assertEquals(restaurantEntityList.get(0).getRestaurantPhone(), restaurantList.get(0).getRestaurantPhone()),
                ()->assertEquals(restaurantEntityList.get(0).getUrlLogo(), restaurantList.get(0).getUrlLogo()),
                ()->assertEquals(restaurantEntityList.get(0).getNit(), restaurantList.get(0).getNit()),
                ()->assertEquals(restaurantEntityList.get(0).getOwnerId(), restaurantList.get(0).getOwnerId())
        );
    }
}