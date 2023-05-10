package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Order;

public interface IAssignOrderPersistentPort {
    void assignedOrder(Integer order, Long employeeId);
}
