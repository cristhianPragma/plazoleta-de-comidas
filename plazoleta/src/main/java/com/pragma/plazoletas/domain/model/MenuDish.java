package com.pragma.plazoletas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDish {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String urlImage;
    private Integer categoryId;
    private Long restaurantId;
    private boolean active;
}
