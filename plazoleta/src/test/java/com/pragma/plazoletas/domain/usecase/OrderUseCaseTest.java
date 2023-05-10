package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.spi.IOrderPersistentPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {
    @Mock
    private IOrderPersistentPort orderPersistentPort;
    @InjectMocks
    private OrderUseCase OrderUseCase;
    @Test
    void saveOderUseCaseTest() {
        Long customerId = 1L;
        List<OrderDish> orderDishList =List.of(new OrderDish(), new OrderDish());
        doNothing().when(orderPersistentPort).saveOder(customerId, orderDishList);
        OrderUseCase.saveOder(customerId, orderDishList);
        verify(orderPersistentPort, times(1)).saveOder(customerId, orderDishList);
    }
}