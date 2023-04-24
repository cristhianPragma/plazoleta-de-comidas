package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.application.mapper.IRoleResponseMapper;
import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleHandlerTest {
    @Mock
    private IRoleServicePort roleServicePort;
    @Mock
    private IRoleResponseMapper roleResponseMapper;
    @InjectMocks
    private RoleHandler roleHandler;

    @Test
    void findByRoleIdTest() {
        int rolId = 1;
        Role role;
        RoleResponseDto roleResponseDto;

         when(roleServicePort.findById(rolId)).thenReturn(role = new Role(1, "Propietario",
                "Gestión de platos y empleados"));
         when(roleResponseMapper.toRoleResponse(role))
                .thenReturn(new RoleResponseDto(1, "Propietario" ));

         roleResponseDto = roleHandler.findByRoleId(rolId);

         assertEquals(rolId, roleResponseDto.getId());
         assertEquals("Propietario", roleResponseDto.getName());
    }
}