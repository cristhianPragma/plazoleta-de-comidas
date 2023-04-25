package com.pragma.plazoletas.application.dto.request;

import jakarta.validation.constraints.Min;
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
public class MenuDishUpdateDto {

    @NotNull(message = "El precio no debe ir nulo")
    @Min(value = 1,  message = "Id incorrecto")
    private Long id;
    @NotNull(message = "El precio no debe ir nulo")
    @Min(value = 1,  message = "El precio debe ser mayor a 0")
    private int price;

    @NotNull(message = "La descripción es requerido")
    @Pattern(regexp = "^[a-zA-Z ]*[a-zA-Z][a-zA-Z ]{2,49}$",
            message = "La descripción debe contener solo letras")
    private String description;
}
