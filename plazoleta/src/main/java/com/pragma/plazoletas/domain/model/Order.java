package com.pragma.plazoletas.domain.model;

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
public class Order {
    private Integer id;
    private Long customerId;
    private LocalDate date;
    private State state;
    private RestaurantEmployee employee;
    private Long restaurantId;
    private List<OrderDish> orderDishList;
}
