package com.pragma.plazoletas.infrastructure.output.clients.adapter;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.clients.feignclients.IUserFeignClient;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerValidationAdapterTest {
    @Mock
    private IUserFeignClient userFeignClient;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private OwnerValidationAdapter ownerValidationAdapter;
    private final String TOKEN ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRo";
    private final String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRo";

    @Test
    void validationOwnerAdapterSuccessTest() {
        Long OWNER_ID = 1L;
        when(jwtService.parseJwt(TOKEN)).thenReturn(JWT);
        when(jwtService.isValidToken(JWT)).thenReturn(true);
        when(userFeignClient.findByRoleId(OWNER_ID))
                .thenReturn(new RoleResponseDto(2,"Propietario"));
        assertDoesNotThrow(() -> ownerValidationAdapter.validation(OWNER_ID, TOKEN));

    }
    @Test
    void validationOwnerAdapterExceptionTest() {
        Long OWNER_ID = 1L;
        when(userFeignClient.findByRoleId(OWNER_ID))
                .thenReturn(new RoleResponseDto(1,"Propietario"));

        RequestException exception = assertThrows(RequestException.class,
                ()->ownerValidationAdapter.validation(OWNER_ID, ""));
        assertEquals("El usuario ingresado no tiene el rol, para esta acción",
                exception.getMessage());
    }
}