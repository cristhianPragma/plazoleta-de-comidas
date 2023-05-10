package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.UserEmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = {"classpath:testapplication.yml"})
class IUserRequestMapperTest {

    @Autowired
    private IUserRequestMapper requestMapper;

    @Test
    void mapperToUserModelTest() {
        UserRequestDto userRequestDto = new UserRequestDto("Carlos", "564488",
                "Dias", "6704985", "carlos@gmail.com", "Carlos1234*");

        User user = requestMapper.toUser(userRequestDto);
        assertAll(
                ()->assertEquals(user.getName(), userRequestDto.getName()),
                ()->assertEquals(user.getDocumentNumber(), userRequestDto.getDocumentNumber()),
                ()->assertEquals(user.getLastName(), userRequestDto.getLastName()),
                ()->assertEquals(user.getPhone(), userRequestDto.getPhone()),
                ()->assertEquals(user.getEmail(), userRequestDto.getEmail()),
                ()->assertEquals(user.getPassword(), userRequestDto.getPassword())
        );
    }

    @Test
    void mapperToUserRequestDtoTest() {
        UserEmployeeRequestDto userEmployeeRequestDto =
                new UserEmployeeRequestDto("Carlos", "564488", "Dias",
                        "6704985", "carlos@gmail.com", "Carlos1234*", 1L);

        UserRequestDto userRequestDto = requestMapper.toUserDto(userEmployeeRequestDto);
        assertAll(
                ()->assertEquals(userEmployeeRequestDto.getName(), userRequestDto.getName()),
                ()->assertEquals(userEmployeeRequestDto.getDocumentNumber(), userRequestDto.getDocumentNumber()),
                ()->assertEquals(userEmployeeRequestDto.getLastName(), userRequestDto.getLastName()),
                ()->assertEquals(userEmployeeRequestDto.getPhone(), userRequestDto.getPhone()),
                ()->assertEquals(userEmployeeRequestDto.getEmail(), userRequestDto.getEmail()),
                ()->assertEquals(userEmployeeRequestDto.getPassword(), userRequestDto.getPassword())
        );
    }
}