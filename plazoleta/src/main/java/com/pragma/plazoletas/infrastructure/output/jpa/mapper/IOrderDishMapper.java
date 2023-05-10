package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.OrderDish;
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
public interface IOrderDishMapper {

    List<OrderDish> toOrderDishModel(List<OrderDishEntity>orderDishEntityList);

    private void setAttributes(OrderDishEntity entity, @MappingTarget OrderDish orderDish) {
        MenuDishEntity menuDishEntity = entity.getMenuDishEntity();
        orderDish.setMenuDish(new MenuDish(menuDishEntity.getId(), menuDishEntity.getName(),
                menuDishEntity.getPrice(), menuDishEntity.getDescription(), menuDishEntity.getUrlImage(),
                new Category(menuDishEntity.getCategory().getId(),menuDishEntity.getCategory().getName(),
                        menuDishEntity.getCategory().getDescription()),
                menuDishEntity.getRestaurant().getId(),menuDishEntity.isActive()));
    }

    @AfterMapping
    default void mapperAttributes(List<OrderDishEntity> entities, @MappingTarget List<OrderDish> orderDish) {
        for (int i = 0; i < orderDish.size(); i++) {
            setAttributes(entities.get(i), orderDish.get(i));
        }
    }
}
