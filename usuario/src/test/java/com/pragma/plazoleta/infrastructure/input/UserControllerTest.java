package com.pragma.plazoleta.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IUserHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IUserHandler userHandler;
    private final int ID_ASSIGN_ROLE = 2;

    @Test
    void saveObjectControllerTest() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto("Carlos", "564488", "Dias",
                "6704985", "carlos@gmail.com", "Carlos1234*");
        doNothing().when(userHandler)
                .saveUser(userRequestDto, ID_ASSIGN_ROLE);

        ResultActions response = mockMvc.perform(post("/api/user/administrator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, times(1)).saveUser(userRequestDto, ID_ASSIGN_ROLE);
        response.andDo(print())
                .andExpect(status().isCreated());

    }
}