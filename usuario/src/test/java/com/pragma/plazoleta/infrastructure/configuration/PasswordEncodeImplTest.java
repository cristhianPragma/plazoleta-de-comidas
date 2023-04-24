package com.pragma.plazoleta.infrastructure.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncodeImplTest {

    @Test
    @DisplayName("Prueba enciptación y validación de contraseña")
    void PasswordEncodeImplencodeTest() {
        PasswordEncodeImpl passwordEncodeImpl = new PasswordEncodeImpl();
        PasswordEncoder bCryptPasswordEncoder = passwordEncodeImpl.passwordEncoder();
        final String PASSWORD ="Admin123";

        String encode = passwordEncodeImpl.encode(PASSWORD);
        assertNotEquals(PASSWORD,encode);
        boolean matches = bCryptPasswordEncoder.matches(PASSWORD,encode);

        assertTrue(matches);
        assertNotEquals(PASSWORD,encode);
    }
}