package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IPasswordEncoder;
import com.pragma.plazoleta.application.handler.IValidationHandler;
import com.pragma.plazoleta.application.mapper.IUserRequestMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
class UserHandlerTest {
    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private IUserRequestMapper userRequestMapper;
    @Mock
    private IPasswordEncoder passwordEncoder;
    @Mock
    private IValidationHandler validationHandler;
    @InjectMocks
    private UserHandler userHandler;
    private final String PASSWORD = "$2a$10$8R1B/sDLrrV3KYi";

    @Test
    void saveUserHandlerTest() {
        User user = null;

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan");
        userRequestDto.setLastName("Martinez");
        userRequestDto.setDocumentNumber("6707941");
        userRequestDto.setEmail("juan@gmail.com");
        userRequestDto.setPhone("315896555");
        userRequestDto.setPassword("Usuario1234*");

        doNothing().when(validationHandler).validate(userRequestDto);
        when(userRequestMapper.toUser(userRequestDto))
                .thenReturn(user = new User(1l,"Juan","125588","Martinez", "6707941",
                        "juan@gmail.com","Usuario1234*", null));
        when(passwordEncoder.encode(user.getPassword())).thenReturn(PASSWORD);
        when(userServicePort.saveUser(user, 1)).thenReturn(1L);

        userHandler.saveUser(userRequestDto, 1);

        assertEquals(PASSWORD,  user.getPassword());
        assertEquals(1l, user.getId());
        assertEquals("Juan", user.getName());
        verify(validationHandler, times(1)).validate(userRequestDto);
        verify(userServicePort, times(1)).saveUser(user, 1);
    }

    @Test
    void roleIdFindUserId(){
        Long userId =1l;
        int roleIdRequest = 2;
        when(userServicePort.roleFindByUserId(userId)).thenReturn(roleIdRequest);

        int roleIdResponse = userHandler.roleIdFindUserId(userId);

        assertEquals(roleIdRequest, roleIdResponse);

    }
}