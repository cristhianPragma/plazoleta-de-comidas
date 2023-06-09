package com.pragma.plazoletas.application.dto.request;

import jakarta.validation.constraints.Min;
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
    @Pattern(regexp = "^[a-zA-Z ]*[a-zA-Z][a-zA-Z ]{2,49}$",
            message = "El nombre debe contener solo letras")
    private String name;

    @NotNull(message = "El precio no debe ir nulo")
    @Min(value = 1,  message = "El precio debe ser mayor a 0")
    private int price;

    @NotNull(message = "La descripción es requerido")
    @Pattern(regexp = "^[a-zA-Z ]*[a-zA-Z][a-zA-Z ]{2,49}$",
            message = "La descripción debe contener solo letras")
    private String description;

    @NotNull(message = "La url del logo es requerida")
    @NotEmpty(message = "La url del logo es requerida")
    private String urlImage;

    @NotNull(message = "Id de categoría nulo")
    @Min(value = 1,  message = "Id de categoría incorrecto")
    private Integer categoryId;

    @NotNull(message = "Id de restaurante nulo")
    @Min(value = 1,  message = "Id de restaurante incorrecto")
    private Long restaurantId;

}
