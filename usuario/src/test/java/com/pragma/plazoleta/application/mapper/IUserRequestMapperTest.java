package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IUserRequestMapperTest {

    @Autowired
    private IUserRequestMapper requestMapper;

    @Test
    void mapperToUserModel() {
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
}