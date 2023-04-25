package com.pragma.plazoletas.application.dto.request;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDto {

    @NotNull(message = "El nombre es requerido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{1,30}(?:\\s[a-zA-Z0-9]{1,30})?$",
            message = "El nombre debe contener al menos una letra")
    private String name;

    @NotNull(message = "El nombre es requerido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d\\s-#]{3,50}$",
            message = "Formato de dirección incorrecto")
    private String address;

    @NotNull(message = "El número telefono es requerido")
    @Pattern(regexp = "^\\+?\\d{6,13}$", message = "El número teléfonico debe contener entre 6 y 13 dígitos")
    private String restaurantPhone;

    @NotNull(message = "La url del logo es requerida")
    @NotEmpty(message = "La url del logo es requerida")
    private String urlLogo;

    @NotNull(message = "El número de NIT es requerido")
    @Pattern(regexp = "^\\+?\\d{3,20}$", message = "El número de NIT debe contener entre 3 y 20 dígitos")
    private String nit;

    @NotNull(message = "Id de propietario nulo")
    @Min(value = 1,  message = "Id de propietario incorrecto")
    private Long ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantRequestDto that = (RestaurantRequestDto) o;
        return Objects.equals(nit, that.nit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nit);
    }
}
