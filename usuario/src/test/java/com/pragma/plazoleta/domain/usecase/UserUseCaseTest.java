package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRoleServicePort rolServicePort;

    @InjectMocks

    private UserUseCase userUseCase;
    @Test
    void saveUserTest() {
        Role role =  new Role(1, "Propietario", "Atender restaurante");
        User user = new User(1l,"Carlos","1234567", "Dias","6707941","carlos@gmail.com",
                "Addim1234+", null);

        when(rolServicePort.findById(1)).thenReturn(role);
        when(userPersistencePort.saveUser(user)).thenReturn(user);

        userUseCase.saveUser(user, 1);

        assertEquals("Propietario", user.getRole().getName());
        verify(userPersistencePort, times(1)).saveUser(user);
    }
    @Test
    void roleFindByUserIdTest(){
        Long userId =1l;
        when(userPersistencePort.findByUserId(userId)).thenReturn(
                new User(userId,"Ana","125489","Mora","3125478",
                        "ana@gmail.com", "125A474*",
                        new Role(2,"Propietario","Gestión de restaurante")));

        int roleId = userUseCase.roleFindByUserId(userId);

        assertEquals(2, roleId);

    }
}