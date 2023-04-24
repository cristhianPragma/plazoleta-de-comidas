package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IRoleResponseMapperTest {
    @Autowired
    private IRoleResponseMapper roleResponseMapper;
    @Test
    void mapperToRoleResponseTest() {
        Role role = new Role(1,"Propiestario", "Gestión de platos y empleados");
        RoleResponseDto roleResponseDto = roleResponseMapper.toRoleResponse(role);
        assertAll(
                ()->assertEquals(role.getId(), roleResponseDto.getId()),
                ()->assertEquals(role.getName(), roleResponseDto.getName())
        );

    }
}