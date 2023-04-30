package com.pragma.plazoleta.infrastructure.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoleta.application.handler.IUserHandler;
import com.pragma.plazoleta.infrastructure.input.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class JwtAuthenticationFilterTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IUserHandler userHandler;
    @BeforeEach
    void setUp(){
    }
    @Test
    public void testPreAuthorizeAnnotation() throws Exception {
       /* Authentication authentication = new UsernamePasswordAuthenticationToken(
                new TestUserDetails(), null, Collections.singleton(new SimpleGrantedAuthority("Administrador")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/prueba"))
                .andExpect(status().isOk());*/
        // Realiza alguna aserción para verificar que la anotación funcionó correctamente
    }
}