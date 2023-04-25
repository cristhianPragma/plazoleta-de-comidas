package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMenuDishEntityMapper {
    @Mapping(target = "menuDish.categoryId", ignore = true)
    @Mapping(target = "menuDish.restaurantId", ignore = true)
    MenuDishEntity toEntity(MenuDish menuDish);

    @Mapping(source = "menuDishEntity.category.id", target = "categoryId")
    @Mapping(source = "menuDishEntity.restaurant.id", target = "restaurantId")
    MenuDish toMenuDishModel(MenuDishEntity menuDishEntity);
}
