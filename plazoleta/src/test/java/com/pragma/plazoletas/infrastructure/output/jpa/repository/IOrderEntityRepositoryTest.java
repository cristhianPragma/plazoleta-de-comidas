package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class IOrderEntityRepositoryTest {
    @Autowired
    private IOrderEntityRepository orderEntityRepository;
    @Autowired
    private IStateEntityRepository stateEntityRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    private OrderEntity orderEntity1;
    private OrderEntity orderEntity2;
    private OrderEntity orderEntity3;
    private StateEntity stateEntity1;
    private StateEntity stateEntity2;
    private StateEntity stateEntity3;
    private RestaurantEntity restaurant1;
    private RestaurantEntity restaurant2;
    private RestaurantEntity restaurant3;
    @BeforeEach
    void setUp(){
        stateEntity1 = stateEntityRepository.save(new StateEntity(null,"Pendiente"));
        stateEntity2 = stateEntityRepository.save(new StateEntity(null,"Preparación"));
        stateEntity3 = stateEntityRepository.save(new StateEntity(null,"Listo"));
        restaurant1 = restaurantRepository.save(new RestaurantEntity(null,"res1", "cra2",
                "2555","logo1","2555",1L));
        restaurant2 = restaurantRepository.save(new RestaurantEntity(null,"res1", "cra2",
                "2555","logo1","2555",1L));
        restaurant3 = restaurantRepository.save(new RestaurantEntity(null,"res1", "cra2",
                "2555","logo1","2555",1L));

        orderEntity1 = orderEntityRepository.save(new OrderEntity(null,1L,
                LocalDate.now(), stateEntity1, null, restaurant1, null));

        orderEntity2 = orderEntityRepository.save(new OrderEntity(null,1L,
                LocalDate.now(), stateEntity1, null, restaurant1, null));

        orderEntity3 = orderEntityRepository.save(new OrderEntity(null,2L,
                LocalDate.now(), stateEntity3, null, restaurant3, null));
    }
    @Test
    void findByCustomerIdTopByOrderByIdDescTest() {
        Optional<OrderEntity> optionalOrderEntity = orderEntityRepository
                .findFirstByCustomerIdOrderByIdDesc(1L);

        assertTrue(optionalOrderEntity.isPresent());
        assertEquals(orderEntity2.getId(), optionalOrderEntity.get().getId());
    }
    @Test
    void findByStateEntityAndEmployeeTest(){
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderEntity> pageOrderEntity = orderEntityRepository
                .findByStateEntityAndRestaurantEntity(stateEntity1, restaurant1, pageRequest);
        assertEquals(pageOrderEntity.getContent().size(), 2);
    }
}