package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.application.handler.IMenuDishOrderListMapper;
import com.pragma.plazoletas.application.handler.IValidateOrderStateClientId;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.domain.api.IOrderServicePort;
import com.pragma.plazoletas.domain.model.OrderDish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OderHandlerTest {
    @Mock
    private IOrderServicePort orderServicePort;
    @Mock
    private IMenuDishOrderListMapper menuDishOrderListMapper;
    @Mock
    private IValidateOrderStateClientId validateOrderStateClientId;
    @Mock
    private IValidationHandler validationHandler;
    @InjectMocks
    private OderHandler oderHandler;

    @Test
    void saverOrderHandlerTest() {
        Long customerId = 1L;
        List<OrderDishRequestDto> orderDto = List.of(new OrderDishRequestDto(),
                new OrderDishRequestDto());
        List<OrderDish>orderModel = List.of(new OrderDish(), new OrderDish());
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdH";

        doNothing().when(validationHandler).validate(orderDto.get(0));
        when(menuDishOrderListMapper.toOrderDishMapper(orderDto)).thenReturn(orderModel);
        when(validateOrderStateClientId.validateState(token)).thenReturn(customerId);
        doNothing().when(orderServicePort).saveOder(customerId, orderModel);

        oderHandler.SaverOrderHandler(orderDto, token);

        verify(menuDishOrderListMapper, times(1))
                .toOrderDishMapper(orderDto);
        verify(validateOrderStateClientId, times(1)).validateState(token);
        verify(orderServicePort, times(1))
                .saveOder(customerId,orderModel);

    }
}