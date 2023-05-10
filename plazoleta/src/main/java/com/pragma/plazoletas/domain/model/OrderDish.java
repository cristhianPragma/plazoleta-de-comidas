package com.pragma.plazoletas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDish {
    private Integer id;
    private MenuDish menuDish;
    private Integer orderId;
    private Integer amount;
}
