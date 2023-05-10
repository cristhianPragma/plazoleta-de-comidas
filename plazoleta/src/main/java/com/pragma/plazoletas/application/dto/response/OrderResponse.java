package com.pragma.plazoletas.application.dto.response;

import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.model.RestaurantEmployee;
import com.pragma.plazoletas.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private Long customerId;
    private LocalDate date;
    private String state;
    private Long employeeId;
    private Long restaurantId;
    private List<OrderDishResponse> orderDishList;
}
