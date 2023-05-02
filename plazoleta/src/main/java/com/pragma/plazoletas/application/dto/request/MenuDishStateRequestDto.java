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
public class MenuDishStateRequestDto {
    @NotNull(message = "El precio no debe ir nulo")
    @Min(value = 1,  message = "Id incorrecto")
    private Long id;

    @NotNull(message = "El estado no debe ir nulo")
    private boolean active;
}
