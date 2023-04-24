package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IValidationHandler;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ValidationAdapterTest {
    private static IValidationHandler validationHandler;
    private UserRequestDto userRequestDto;

    @BeforeAll
    static void setUp(){
        validationHandler = new ValidationAdapter();
    }
    @BeforeEach
    void setUpOneTeOne(){
        userRequestDto = new UserRequestDto("Carlos", "564488", "Dias",
                "6704985", "carlos@gmail.com", "Carlos1234*");
    }

    @Test
    void validateUserSuccessTest() {
        assertDoesNotThrow(() -> validationHandler.validate(userRequestDto));
    }
    @Test
    void validateUserNullTest() {
        userRequestDto = null;
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El usuario no puede ser nulo", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "22dcc", " "})
    void validateNameTestException(String name) {
        userRequestDto.setName(name);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El nombre debe contener solo letras", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "22dcc", " "})
    void validateDocumentNumberTestException(String documentNumber) {
        userRequestDto.setDocumentNumber(documentNumber);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El número de documento debe contener entre 5 y 15 dígitos", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "22dcc", " "})
    void validateLastNameTestException(String lastName) {
        userRequestDto.setLastName(lastName);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El  apellido debe contener solo letras",
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "22dcc", " "})
    void validatePhoneTestException(String phone) {
        userRequestDto.setPhone(phone);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El número teléfonico debe contener entre 6 y 13 dígitos",
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"correo1", "correoalgo@algo", " ","","correoalgo@algo."})
    void validateEmailTestException(String mail) {
        userRequestDto.setEmail(mail);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("El email no es válido", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "22dcc", " ","clslsls23555ss"})
    void validatePasswordTestException(String password) {
        userRequestDto.setPassword(password);
        RequestException exception = assertThrows(RequestException.class,
                ()->validationHandler.validate(userRequestDto));
        assertEquals("La clave debe contener al menos una mayúscula, una minúscula, un número y un caracter especial",
                exception.getMessage());
    }
}