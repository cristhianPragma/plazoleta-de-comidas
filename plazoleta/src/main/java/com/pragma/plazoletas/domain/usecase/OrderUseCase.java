package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IOrderServicePort;
import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.spi.IOrderPersistentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistentPort orderPersistentPort;
    @Override
    public void saveOder(Long customerId, List<OrderDish> orderDishList) {
        orderPersistentPort.saveOder(customerId, orderDishList);
    }

    @Override
    public List<Order> listOrder(Integer stateId, int pageSize, int pageNumber, Long employeeId) {
        return orderPersistentPort.listOrder(stateId, pageSize, pageNumber, employeeId);
    }
}
