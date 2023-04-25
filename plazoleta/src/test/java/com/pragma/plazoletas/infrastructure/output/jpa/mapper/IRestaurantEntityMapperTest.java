package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}