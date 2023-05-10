package com.pragma.plazoletas.infrastructure.output.jpa.mapper;

import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {
    RestaurantEmployee toModelEmployee(RestaurantEmployeeEntity restaurantEmployee);
    RestaurantEmployeeEntity toEntityEmployee(RestaurantEmployee restaurantEmployee);
}
