package com.pragma.plazoleta.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoleta.application.dto.request.LoginRequest;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.JwtResponse;
import com.pragma.plazoleta.application.handler.IUserHandler;
import com.pragma.plazoleta.infrastructure.security.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IUserHandler userHandler;
    @MockBean
    private AuthService authService;
    private int idAssignRole = 4;
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();

        userRequestDto = new UserRequestDto("Carlos", "564488", "Dias",
                "6704985", "carlos@gmail.com", "Carlos1234*");
    }

    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Administrador")
    @Test
    void saveOwnerControllerSuccessResponseTest() throws Exception {
        idAssignRole =2;
        doNothing().when(userHandler)
                .saveUser(userRequestDto, idAssignRole);

        ResultActions response = mockMvc.perform(post("/users/administrator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, times(1)).saveUser(userRequestDto, idAssignRole);
        response.andDo(print())
                .andExpect(status().isCreated());

    }

    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    @Test
    void saveOwnerControllerForbiddenResponseTest() throws Exception {
        idAssignRole =2;
        doNothing().when(userHandler)
                .saveUser(userRequestDto, idAssignRole);

        ResultActions response = mockMvc.perform(post("/users/administrator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, never()).saveUser(userRequestDto, idAssignRole);
        response.andDo(print())
                .andExpect(status().isForbidden());

    }

    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    @Test
    void saveEmployeeControllerSuccessTest() throws Exception {
        idAssignRole =3;
        doNothing().when(userHandler)
                .saveUser(userRequestDto, idAssignRole);

        ResultActions response = mockMvc.perform(post("/users/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, times(1)).saveUser(userRequestDto, idAssignRole);
        response.andDo(print())
                .andExpect(status().isCreated());

    }

    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Administrador")
    @Test
    void saveEmployeeControllerForbiddenTest() throws Exception {
        idAssignRole =3;
        doNothing().when(userHandler)
                .saveUser(userRequestDto, idAssignRole);

        ResultActions response = mockMvc.perform(post("/users/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, never()).saveUser(userRequestDto, idAssignRole);
        response.andDo(print())
                .andExpect(status().isForbidden());

    }

    @Test
    void loginUserControllerSuccessTest() throws Exception {
        LoginRequest loginRequest= new LoginRequest("user","pass");
        JwtResponse jwt = new JwtResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ");

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(jwt);

        ResultActions response = mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(jwt.getToken())));

    }


    @Test
    void saveCustomerControllerSuccessTest() throws Exception {
        idAssignRole =4;
        doNothing().when(userHandler)
                .saveUser(userRequestDto, idAssignRole);

        ResultActions response = mockMvc.perform(post("/users/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)));

        verify(userHandler, times(1)).saveUser(userRequestDto, idAssignRole);
        response.andDo(print())
                .andExpect(status().isCreated());

    }

}