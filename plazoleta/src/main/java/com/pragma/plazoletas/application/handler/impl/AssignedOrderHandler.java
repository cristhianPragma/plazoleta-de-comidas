package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.handler.IAssignedOrder;
import com.pragma.plazoletas.domain.api.IAssignOrderServicePort;
import com.pragma.plazoletas.infrastructure.security.jwt.IExtractAndValidateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignedOrderHandler implements IAssignedOrder {
    private final IAssignOrderServicePort assignOrderServicePort;
    private final IExtractAndValidateToken extractAndValidateToken;

    @Override
    public void assignedOrder(Integer orderId, String token){
        Long employeeId = extractAndValidateToken.extract(token);
        assignOrderServicePort.assignedOrder(orderId, employeeId);
    }
}
