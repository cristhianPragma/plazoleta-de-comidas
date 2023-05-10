package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.model.State;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityListMapper {
    List<Order> orderList(List<OrderEntity> orderEntityList);

    private void setAttributes(OrderEntity entity, @MappingTarget Order order) {
        order.setState(new State(entity.getStateEntity().getId(),
                entity.getStateEntity().getDescription()));
        order.setRestaurantId(entity.getRestaurantEntity().getId());

//                menuDishEntity.getPrice(), menuDishEntity.getDescription(), menuDishEntity.getUrlImage(),
//                new Category(menuDishEntity.getCategory().getId(),menuDishEntity.getCategory().getName(),
//                        menuDishEntity.getCategory().getDescription()),
//                menuDishEntity.getRestaurant().getId(),menuDishEntity.isActive()));
    }

    @AfterMapping
    default void mapperAttributes(List<OrderEntity> entities, @MappingTarget List<Order> order) {
        for (int i = 0; i < order.size(); i++) {
            setAttributes(entities.get(i), order.get(i));
        }
    }
}
