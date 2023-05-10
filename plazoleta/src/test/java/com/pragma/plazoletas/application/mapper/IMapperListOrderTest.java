package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.response.OrderResponse;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.model.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IMapperListOrderTest {
    @Autowired
    private IMapperListOrder mapperListOrder;
    @Test
    void toOrderResponseTest() {
        List<Order> orderList = List.of(new Order(1,1L, LocalDate.now(),
                new State(1,"Preparado"),null, 1L,
                List.of(new OrderDish(1,new MenuDish(1L,"Sopa", 500,"sopa",
               "sopa.png",null,1L, true),1,3))));

        List<OrderResponse>orderResponses = mapperListOrder.toOrderResponse(orderList);
        assertAll(
                ()-> assertEquals(orderList.get(0).getDate(), orderResponses.get(0).getDate()),
                ()-> assertEquals(orderList.get(0).getOrderDishList().get(0).getMenuDish().getName(),
                        orderResponses.get(0).getOrderDishList().get(0).getName()),
                ()-> assertEquals(orderList.get(0).getOrderDishList().get(0).getAmount(),
                orderResponses.get(0).getOrderDishList().get(0).getAmount())
        );

    }
}