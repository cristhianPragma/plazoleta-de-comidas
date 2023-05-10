package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.application.dto.response.OrderResponse;

import java.util.List;

public interface IOrderHandler {
    void SaverOrderHandler(List<OrderDishRequestDto> orderDto, String token);
    public List<OrderResponse> listOrderResponseHandler(Integer stateId, int pageSize, int pageNumber, String token);
}
