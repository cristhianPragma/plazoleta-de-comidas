package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
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
        id = restaurantRepository.save(restaurant).getId()+"";
        System.out.println(restaurant.getId());
        Optional<RestaurantEntity> restaurantFound= restaurantRepository
                .findByIdOrNit(Long.valueOf(id), nit);
        assertTrue(restaurantFound.isPresent());

    }
    @ParameterizedTest
    @CsvSource({"2,0", "3,0", "4,0"})
    void listRestaurantPagedTest(int pageSize, int pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        List<RestaurantEntity> restaurantEntityList = List.of(
                new RestaurantEntity(null,"restaurante 1", "cra 2 N 162",
                        "1255666", "http://img.png","1125555", ownerId),
                new RestaurantEntity(null,"restaurante 2", "cra 3 N 162",
                        "1255666", "http://img2.png","1125555", ownerId),
                new RestaurantEntity(null,"algo para comer 3", "cra 4 N 162",
                        "1255666", "http://img3.png","1125555", ownerId),
                new RestaurantEntity(null,"restaurante 4", "cra 5 N 162",
                        "1255666", "http://img4.png","1125555", ownerId),
                new RestaurantEntity(null,"restaurante 5", "cra 4 N 162",
                "1255666", "http://img5.png","1125555", ownerId),
                new RestaurantEntity(null,"restaurante 6", "cra 5 N 162",
                        "1255666", "http://img6.png","1125555", ownerId));
        restaurantRepository.saveAll(restaurantEntityList);

        Page<RestaurantEntity>restaurantEntityPage = restaurantRepository.findAll(pageRequest);
        List<RestaurantEntity>restaurantListResponse = restaurantEntityPage.getContent();

        assertEquals(pageSize, restaurantListResponse.size());
        assertEquals("algo para comer 3", restaurantListResponse.get(0).getName());
    }
}