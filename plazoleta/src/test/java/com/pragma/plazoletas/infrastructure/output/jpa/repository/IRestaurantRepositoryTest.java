package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class IRestaurantRepositoryTest {
    @Autowired
    private IRestaurantRepository restaurantRepository;
    private Long restaurantId = 1L;
    private Long ownerId = 2L;
    @ParameterizedTest
    @CsvSource({"1,1588222", "7,1125555"})
    @DisplayName("Busqueda de restaurante por nit o por id")
    void findByIdOrNitRestaurantRepositoryTest(String  id, String nit) {

        RestaurantEntity restaurant = new RestaurantEntity(restaurantId,"restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", ownerId);
        restaurantRepository.save(restaurant);

        Optional<RestaurantEntity> restaurantFound= restaurantRepository
                .findByIdOrNit(Long.valueOf(id), nit);

        assertTrue(restaurantFound.isPresent());

    }
}