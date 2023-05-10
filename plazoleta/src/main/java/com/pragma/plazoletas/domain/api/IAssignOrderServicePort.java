package com.pragma.plazoletas.domain.api;

import com.pragma.plazoletas.domain.model.Order;

public interface IAssignOrderServicePort {
    void assignedOrder(Integer order, Long employeeId);
}
