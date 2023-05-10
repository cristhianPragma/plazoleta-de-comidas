package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.spi.IAssignOrderPersistentPort;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class AssignedOrderAdapter implements IAssignOrderPersistentPort {
     private final IOrderEntityRepository orderEntityRepository;
     private final IRestaurantEmployeeRepository restaurantEmployeeRepository;

    @Override
    public void assignedOrder(Integer order, Long employeeId) {
        RestaurantEmployeeEntity employee = restaurantEmployeeRepository.findById(employeeId)
                .orElseThrow(()->new RequestException("Empleado no encontrado", HttpStatus.NOT_FOUND));

        OrderEntity orderEntity = orderEntityRepository.findById(order)
                .orElseThrow(()->new RequestException("Pedido no encontrado", HttpStatus.NOT_FOUND));

        if (employee.getRestaurantId() != orderEntity.getRestaurantEntity().getId())
            throw new RequestException("El pedido no pertecen al mismo restaurante del empleado", HttpStatus.BAD_REQUEST);

        orderEntity.setEmployee(employee);
        orderEntityRepository.save(orderEntity);
    }
}
