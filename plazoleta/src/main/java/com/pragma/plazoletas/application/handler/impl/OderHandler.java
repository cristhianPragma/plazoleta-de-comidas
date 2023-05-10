package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.application.dto.response.OrderResponse;
import com.pragma.plazoletas.application.handler.IMenuDishOrderListMapper;
import com.pragma.plazoletas.application.handler.IOrderHandler;
import com.pragma.plazoletas.application.handler.IValidateOrderStateClientId;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IMapperListOrder;
import com.pragma.plazoletas.domain.api.IOrderServicePort;
import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.security.jwt.IExtractAndValidateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IMenuDishOrderListMapper menuDishOrderListMapper;
    private final IValidateOrderStateClientId validateOrderStateClientId;
    private final IValidationHandler validationHandler;
    private final IMapperListOrder mapperListOrder;
    private final IExtractAndValidateToken extractAndValidateToken;
    @Override
    public void SaverOrderHandler(List<OrderDishRequestDto>orderDto, String token){
        orderDto.forEach(validationHandler::validate);
        List<OrderDish>orderModel = menuDishOrderListMapper.toOrderDishMapper(orderDto);
        Long customerId = validateOrderStateClientId.validateState(token);
        orderServicePort.saveOder(customerId,orderModel);
    }
    @Override
    public List<OrderResponse> listOrderResponseHandler(Integer stateId, int pageSize, int pageNumber, String token){
        Long employeeId = extractAndValidateToken.extract(token);
        return mapperListOrder.toOrderResponse(orderServicePort
                .listOrder(stateId, pageSize, pageNumber, employeeId));
    }

}
