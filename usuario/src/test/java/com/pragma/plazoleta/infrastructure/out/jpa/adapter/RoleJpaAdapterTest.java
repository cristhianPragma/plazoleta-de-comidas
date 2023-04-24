package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRoleEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RoleJpaAdapterTest {
    @Mock
    private IRoleEntityRepository roleEntityRepository;
    @Mock
    private IRoleEntityMapper roleEntityMapper;
    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;
    @Test
    @DisplayName("Busqueda exitosa de rol")
    void findRoleById() {
        Optional<RoleEntity>optionaRole = Optional.empty();
        Role role;

        when(roleEntityRepository.findById(1)).thenReturn(optionaRole = Optional.of(
                new RoleEntity(1, "Propietario"," Atender restaurante")));

        when(roleEntityMapper.toRole(optionaRole.get())).thenReturn(
                role =new Role(1, "Propietario"," Atender restaurante"));

        Role returnRole = roleJpaAdapter.findRoleById(1);

        assertTrue(optionaRole.isPresent());
        assertEquals("Propietario", optionaRole.get().getName());
        assertEquals(1, returnRole.getId());
    }

    @Test
    @DisplayName("Excepción de validación cuando no se existe el rol con el id ingresado")
    void findRoleByIdException(){
        when(roleEntityRepository.findById(2)).thenReturn(Optional.empty());
        RequestException e = assertThrows(RequestException.class,()->roleJpaAdapter.findRoleById(2));
        assertEquals("Rol no encontrado", e.getMessage());
        verify(roleEntityMapper, Mockito.never()).toRole(Mockito.any(RoleEntity.class));
    }
}