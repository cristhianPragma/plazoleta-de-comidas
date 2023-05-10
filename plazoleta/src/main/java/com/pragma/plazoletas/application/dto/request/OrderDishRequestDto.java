package com.pragma.plazoletas.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequestDto {
    @NotNull(message = "El id del plato no debe ir nulo")
    @Min(value = 1,  message = "Id de plato invalido")
    private Long menuDishId;

    @NotNull(message = "La cantidad no debe ir nula")
    @Min(value = 1,  message = "La cantidad debe ser mayor a cero")
    private Integer amount;
}
