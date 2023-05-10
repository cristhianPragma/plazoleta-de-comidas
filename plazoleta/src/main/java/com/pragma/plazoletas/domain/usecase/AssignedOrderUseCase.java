package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IAssignOrderServicePort;
import com.pragma.plazoletas.domain.spi.IAssignOrderPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignedOrderUseCase implements IAssignOrderServicePort {
    private final IAssignOrderPersistentPort assignOrderPersistentPort;
    @Override
    public void assignedOrder(Integer order, Long employeeId) {
        assignOrderPersistentPort.assignedOrder(order, employeeId);
    }
}
