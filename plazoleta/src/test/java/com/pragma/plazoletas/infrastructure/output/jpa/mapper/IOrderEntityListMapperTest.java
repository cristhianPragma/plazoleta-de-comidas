package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IOrderEntityListMapperTest {
    @Autowired
    IOrderEntityListMapper orderEntityListMapper;
    @Autowired
    IOrderDishMapper orderDishMapper;

    @Test
    void orderEntityListMapperTest() {
        List<OrderEntity> orderEntityList = List.of(new OrderEntity(1,1L, LocalDate.now(),
                new StateEntity(1,"Pendiente"),new RestaurantEmployeeEntity(1L,3L),
                new RestaurantEntity(1L,"res","cra3","3696","logo","1225",1L),
                List.of(new OrderDishEntity(1, new MenuDishEntity(1L,"Sopa", 500,
                        "sopa", "sopa.png", new CategoryMenuDishEntity(1,"sopas",
                "sopas"),new RestaurantEntity(),
                        true),null,3))));

        List<Order> orderList = orderEntityListMapper.orderList(orderEntityList);
        orderList.get(0).setOrderDishList(orderDishMapper
                .toOrderDishModel(orderEntityList.get(0).getOrderDishList()));

        assertAll(
                ()-> assertEquals(orderEntityList.get(0).getId(), orderList.get(0).getId()),
                ()-> assertEquals(orderEntityList.get(0).getDate(), orderList.get(0).getDate()),
                ()-> assertEquals(orderEntityList.get(0).getOrderDishList().get(0).getId(),
                        orderList.get(0).getOrderDishList().get(0).getId()),

                ()-> assertEquals(orderEntityList.get(0).getOrderDishList().get(0).getMenuDishEntity().getName(),
                        orderList.get(0).getOrderDishList().get(0).getMenuDish().getName()),

                ()-> assertEquals(orderEntityList.get(0).getStateEntity().getId(),
                        orderList.get(0).getState().getId()),

                ()->assertEquals(orderEntityList.get(0).getEmployee().getUserId(),
                        orderList.get(0).getEmployee().getUserId()),

                ()->assertEquals(orderEntityList.get(0).getRestaurantEntity().getId(),
                        orderList.get(0).getRestaurantId())
        );
    }
}