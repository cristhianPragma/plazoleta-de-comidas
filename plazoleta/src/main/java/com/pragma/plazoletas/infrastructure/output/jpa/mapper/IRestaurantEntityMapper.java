package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toRestaurantModel(RestaurantEntity entity);
}
