package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void testRolefindById() {
        when(rolePersistencePort.findRoleById(1)).thenReturn(
                new Role(1, "Propietario", "Atender restaurante"));

        Role findRole = roleUseCase.findById(1);
        assertNotNull(findRole);
        assertEquals("Propietario", findRole.getName());

    }
}