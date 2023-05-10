package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.handler.IValidateOrderStateClientId;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.security.jwt.IExtractAndValidateToken;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateOrderState implements IValidateOrderStateClientId {
    private final IOrderEntityRepository orderEntityRepository;
    private final IExtractAndValidateToken extractAndValidateToken;
    @Override
    public Long validateState(String token) {
        Long customerId = extractAndValidateToken.extract(token);
        OrderEntity orderEntity = orderEntityRepository.findFirstByCustomerIdOrderByIdDesc(customerId).orElse(null);
        if (orderEntity == null)
            return customerId;

        if (orderEntity.getStateEntity().getId() != 4 && orderEntity.getStateEntity().getId() != 5)
            throw new RequestException("El usuario no puede solicitar más pedidos, tiene un pedido " +
                    "con estado: "+orderEntity.getStateEntity().getDescription(), HttpStatus.BAD_REQUEST);

        return customerId;
    }
}
