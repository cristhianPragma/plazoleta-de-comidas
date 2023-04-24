package com.pragma.plazoletas.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDishRequestDto {
    @NotNull(message = "El nombre es requerido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,30}$", message = "El nombre debe al menos una letra")
    private String name;


    private int price;

    @NotNull(message = "El nombre es requerido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,30}$", message = "El nombre debe al menos una letra")
    private String description;

    @NotNull(message = "La url del logo es requerida")
    @NotEmpty(message = "La url del logo es requerida")
    private String urlImage;

    @NotNull(message = "Id de propietario nulo")
    private Integer categoryId;
    private Integer restaurantId;
}
