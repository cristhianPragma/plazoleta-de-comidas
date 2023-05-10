package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployRequestMapper {
    RestaurantEmployee toRestaurantEmployeeModel(EmployeeRequestDto employeeRequestDto);
}
