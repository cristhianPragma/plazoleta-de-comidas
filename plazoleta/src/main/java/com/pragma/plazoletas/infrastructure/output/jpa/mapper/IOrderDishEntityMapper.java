package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {

    List<OrderDishEntity> toOrderDishEntity(List<OrderDish>orderDishList);

    private void setAttributes(OrderDish orderDish, @MappingTarget OrderDishEntity entity) {
        MenuDish menuDish = orderDish.getMenuDish();
        entity.setMenuDishEntity(new MenuDishEntity(menuDish.getId(), menuDish.getName(),
                menuDish.getPrice(), menuDish.getDescription(), menuDish.getUrlImage(),
                new CategoryMenuDishEntity(menuDish.getCategory().getId(),menuDish.getCategory().getName(),
                        menuDish.getCategory().getDescription()),
                null,menuDish.isActive()));
    }

    @AfterMapping
    default void mapperAttributes(List<OrderDish> orderDishList, @MappingTarget List<OrderDishEntity> orderDishEntityList) {
        for (int i = 0; i < orderDishEntityList.size(); i++) {
            setAttributes(orderDishList.get(i), orderDishEntityList.get(i));
        }
    }
}
