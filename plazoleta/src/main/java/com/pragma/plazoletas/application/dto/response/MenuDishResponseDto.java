package com.pragma.plazoletas.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDishResponseDto {
    private String name;
    private String description;
    private int price;
    private String urlImage;
    private String categoryName;
}
