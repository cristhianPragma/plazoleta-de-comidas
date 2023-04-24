package com.pragma.plazoletas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String restaurantPhone;
    private String urlLogo;
    private String nit;
    private Long ownerId;

}
