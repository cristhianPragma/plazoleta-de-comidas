package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.domain.model.MenuDish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMenuDishRequestMapper {
    MenuDish toMenuDish(MenuDishRequestDto menuDishRequestDto);
}
