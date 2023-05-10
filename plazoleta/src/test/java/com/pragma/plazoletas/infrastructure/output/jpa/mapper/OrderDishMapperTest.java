package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class OrderDishMapperTest {
    @Autowired
    IOrderDishMapper orderMapper;
    @Autowired
    IOrderDishEntityMapper orderDishMapperEntityList;
    @Test
    void toOrderModelMapperTest() {
        MenuDishEntity entity = new MenuDishEntity(1L,"Sopa",500,"Sopa de arroz",
                "sopa.png", new CategoryMenuDishEntity(1,"sopas","Ricas sopas"),
                new RestaurantEntity(1L,"Cocos","cra2 164","3255555",
                        "Logo.png","56666555",1L), true);

        MenuDishEntity entity2 = new MenuDishEntity(2L,"Sopa2",800,"Sopa de colicero",
                "sopa.png", new CategoryMenuDishEntity(2,"sopas2","Ricas sopas"),
                new RestaurantEntity(2L,"Cocos2","cra4 168","1556665",
                        "Logo2.png","10000555",2L), true);

        List<OrderDishEntity>orderDishEntityList = List
                .of(new OrderDishEntity(1,entity,new OrderEntity(),4),
                new OrderDishEntity(1,entity2,new OrderEntity(),3));

        List<OrderDish>orderDishList = orderMapper.toOrderDishModel(orderDishEntityList);
        assertAll(
                ()->assertEquals(orderDishEntityList.size(), orderDishList.size()),
                ()->assertEquals(orderDishEntityList.get(0).getId(), orderDishList.get(0).getId()),
                ()->assertEquals(orderDishEntityList.get(0).getAmount(), orderDishList.get(0).getAmount()),
                ()->assertEquals(orderDishEntityList.get(0).getMenuDishEntity().getRestaurant().getId(),
                        orderDishList.get(0).getMenuDish().getRestaurantId()),
                ()->assertEquals(orderDishEntityList.get(0).getMenuDishEntity().getId(),
                        orderDishList.get(0).getMenuDish().getId()),
                ()->assertEquals(orderDishEntityList.get(0).getMenuDishEntity().getCategory().getName(),
                        orderDishList.get(0).getMenuDish().getCategory().getName())

        );
    }

    @Test
    void toOrderEntityMapperTest() {
        MenuDish menuDish = new MenuDish(1L,"Sopa",500,"Sopa de arroz",
                "sopa.png", new Category(1,"sopas","Ricas sopas"),
                1L, true);

        MenuDish menuDish2 = new MenuDish(2L,"Sopa2",2500,"Sopa de colicero",
                "sopa2.png", new Category(2,"sopas2","Ricas sopas"),
                2L, true);

        List<OrderDish>orderDishList = List
                .of(new OrderDish(1,menuDish,1,4),
                        new OrderDish(2,menuDish2,2,2));

        List<OrderDishEntity>entities = orderDishMapperEntityList.toOrderDishEntity(orderDishList);

        assertAll(
                ()->assertEquals(orderDishList.size(), entities.size()),
                ()->assertEquals(orderDishList.get(0).getId(), entities.get(0).getId()),
                ()->assertEquals(orderDishList.get(0).getAmount(), entities.get(0).getAmount()),
                ()->assertEquals(orderDishList.get(0).getMenuDish().getId(),
                        entities.get(0).getMenuDishEntity().getId()),
                ()->assertEquals(orderDishList.get(0).getMenuDish().getCategory().getName(),
                        entities.get(0).getMenuDishEntity().getCategory().getName())

        );
    }

}
