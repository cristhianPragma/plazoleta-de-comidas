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
public class EmployeeRequestDto {
    @NotNull(message = "El id del restaurante no debe ir nulo")
    @Min(value = 1,  message = "Id de restaurante invalido")
    private Long restaurantId;

    @NotNull(message = "El id del usuario no debe ir nulo")
    @Min(value = 1,  message = "Id de usuario invalido")
    private Long userId;
}
