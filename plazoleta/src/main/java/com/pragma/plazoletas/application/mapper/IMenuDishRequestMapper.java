package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.response.MenuDishResponseDto;
import com.pragma.plazoletas.domain.model.MenuDish;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMenuDishRequestMapper {
    @Mapping(source = "menuDishRequestDto.categoryId", target = "category.id")
    MenuDish toMenuDish(MenuDishRequestDto menuDishRequestDto);
    @Mapping(target = "categoryName", source = "category.name")
    List<MenuDishResponseDto> toMenuDishResponseDto(List<MenuDish> menuDish);
    private void setCategoryName(MenuDish menuDish, @MappingTarget MenuDishResponseDto dto) {
        dto.setCategoryName(menuDish.getCategory().getName());
    }
    @AfterMapping
    default void setCategoryName(List<MenuDish> menuDish, @MappingTarget List<MenuDishResponseDto> dtoList) {
        for (int i = 0; i < menuDish.size(); i++) {
            setCategoryName(menuDish.get(i), dtoList.get(i));
        }
    }
}
