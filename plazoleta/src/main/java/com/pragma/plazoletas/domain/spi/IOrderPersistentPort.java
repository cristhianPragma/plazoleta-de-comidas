package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.model.State;

import java.util.List;

public interface IOrderPersistentPort {
    void saveOder(Long customerId, List<OrderDish> orderDishList);
    List<Order>listOrder(Integer stateId,  int pageSize, int pageNumber, Long employeeId);

}
