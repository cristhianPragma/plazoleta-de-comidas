package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMenuDishEntityMapper {
    @Mapping(target = "menuDish.categoryId", ignore = true)
    @Mapping(target = "menuDish.restaurantId", ignore = true)
    MenuDishEntity toEntity(MenuDish menuDish);

    @Mapping(source = "menuDishEntity.category.id", target = "category.id")
    @Mapping(source = "menuDishEntity.restaurant.id", target = "restaurantId")
    @Mapping(source = "menuDishEntity.category.name", target = "category.name")
    MenuDish toMenuDishModel(MenuDishEntity menuDishEntity);
    @Mapping(target = "category.id", source = "category.id")
    @Mapping(target = "category.name", source = "category.name")
    List<MenuDish>toMenuDishModelList(List<MenuDishEntity> menuDishEntities);
    private void setCategoryIdAndName(MenuDishEntity entity, @MappingTarget MenuDish menuDish) {
        menuDish.getCategory().setId(entity.getCategory().getId());
        menuDish.getCategory().setName(entity.getCategory().getName());
    }
    @AfterMapping
    default void setCategory(List<MenuDishEntity> entities, @MappingTarget List<MenuDish> menuDish) {
        for (int i = 0; i < menuDish.size(); i++) {
            setCategoryIdAndName(entities.get(i), menuDish.get(i));
        }
    }
}
