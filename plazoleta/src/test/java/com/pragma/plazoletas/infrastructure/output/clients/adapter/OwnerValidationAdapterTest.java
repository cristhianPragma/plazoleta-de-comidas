package com.pragma.plazoletas.infrastructure.output.clients.adapter;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.clients.feignclients.IUserFeignClietn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerValidationAdapterTest {
    @Mock
    private IUserFeignClietn userFeignClient;
    @InjectMocks
    private OwnerValidationAdapter ownerValidationAdapter;

    @Test
    void validationOwnerAdapterSuccessTest() {
        Long OWNER_ID = 1L;
        when(userFeignClient.findByRoleId(OWNER_ID))
                .thenReturn(new RoleResponseDto(2,"Propietario"));
        assertDoesNotThrow(() -> ownerValidationAdapter.validation(OWNER_ID));

    }
    @Test
    void validationOwnerAdapterExceptionTest() {
        Long OWNER_ID = 1L;
        when(userFeignClient.findByRoleId(OWNER_ID))
                .thenReturn(new RoleResponseDto(1,"Propietario"));

        RequestException exception = assertThrows(RequestException.class,
                ()->ownerValidationAdapter.validation(OWNER_ID));
        assertEquals("El usuario ingresado no tiene el rol, para esta acción",
                exception.getMessage());
    }
}