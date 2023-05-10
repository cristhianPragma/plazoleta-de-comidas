package com.pragma.plazoleta.application.dto.request;
import jakarta.validation.constraints.Min;
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
public class UserEmployeeRequestDto {
    @NotNull(message = "El nombre es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúñÑÁÉÍÓÚ]{3,30}$", message = "El nombre debe contener solo letras")
    private String name;

    @NotNull(message = "El número de documento es requerido")
    @Pattern(regexp = "^\\d{5,15}$", message = "El número de documento debe contener entre 5 y 15 dígitos")
    private String documentNumber;

    @NotNull(message = "El apellido es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúñÑÁÉÍÓÚ]{3,30}$", message = "El  apellido debe contener solo letras")
    private String lastName;

    @NotNull(message = "El número telefono es requerido")
    @Pattern(regexp = "^\\+?\\d{6,13}$", message = "El número teléfonico debe contener entre 6 y 13 dígitos")
    private String phone;

    @NotNull(message = "El email es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]{2,30}@[a-zA-Z0-9.-]{2,20}\\.[a-zA-Z]{2,10}$",
            message = "El email no es válido")
    private String email;

    @NotNull(message = "La clave es requerida")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
    message = "La clave debe contener al menos una mayúscula, una minúscula, un número y un caracter especial")
    private String password;

    @NotNull(message = "El id del restaurante es requerido")
    @Min(value = 1,  message = "El precio debe ser mayor a 0")
    private Long restaurantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEmployeeRequestDto userDto = (UserEmployeeRequestDto) o;
        return Objects.equals(documentNumber, userDto.documentNumber)
                || Objects.equals(phone, userDto.phone)
                || Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, phone, email);
    }
}
