package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.security.jwt.ExtractAndValidateToken;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ValidateOrderStateTest {
    @Mock
    private ExtractAndValidateToken extractAndValidateToken;
    @Mock
    private IOrderEntityRepository orderEntityRepository;
    @InjectMocks
    private ValidateOrderState validateOrderState;
    @Test
    void validateNullStateAdapterTest() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdH";
        Long customerId = 1L;
        when(extractAndValidateToken.extract(token)).thenReturn(customerId);
        when(orderEntityRepository.findFirstByCustomerIdOrderByIdDesc(customerId))
                .thenReturn(Optional.empty());

        Long customerIdReturned = validateOrderState.validateState(token);

        verify(extractAndValidateToken, times(1)).extract(token);
        verify(orderEntityRepository, times(1)).findFirstByCustomerIdOrderByIdDesc(customerId);
        assertEquals(customerId, customerIdReturned);
    }
    @Test
    void validateStateAdapterTest() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdH";
        Long customerId = 1L;
        when(extractAndValidateToken.extract(token)).thenReturn(customerId);
        when(orderEntityRepository.findFirstByCustomerIdOrderByIdDesc(customerId))
                .thenReturn(Optional.of(new OrderEntity(1, customerId, LocalDate.now(),
                        new StateEntity(4, "Entregado"), null, new RestaurantEntity(),
                        List.of(new OrderDishEntity()))));

        Long customerIdReturned = validateOrderState.validateState(token);

        verify(extractAndValidateToken, times(1)).extract(token);
        verify(orderEntityRepository, times(1)).findFirstByCustomerIdOrderByIdDesc(customerId);
        assertEquals(customerId, customerIdReturned);
    }
    @Test
    void validateStateAdapterExceptionTest() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdH";
        Long customerId = 1L;
        when(extractAndValidateToken.extract(token)).thenReturn(customerId);
        when(orderEntityRepository.findFirstByCustomerIdOrderByIdDesc(customerId))
                .thenReturn(Optional.of(new OrderEntity(1, customerId, LocalDate.now(),
                        new StateEntity(1, "Pendiente"), null, new RestaurantEntity(),
                        List.of(new OrderDishEntity()))));

        RequestException requestException = assertThrows(RequestException.class,
                ()-> validateOrderState.validateState(token));

        verify(extractAndValidateToken, times(1)).extract(token);
        verify(orderEntityRepository, times(1)).findFirstByCustomerIdOrderByIdDesc(customerId);
        assertEquals("El usuario no puede solicitar más pedidos, tiene un pedido " +
                "con estado: Pendiente", requestException.getMessage());
    }

}