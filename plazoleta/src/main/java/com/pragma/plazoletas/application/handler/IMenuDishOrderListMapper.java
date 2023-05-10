package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.domain.model.OrderDish;

import java.util.List;

public interface IMenuDishOrderListMapper {
    List<OrderDish> toOrderDishMapper(List<OrderDishRequestDto> orderDishRequestDtoList);
}
