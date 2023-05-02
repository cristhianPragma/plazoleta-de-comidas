package com.pragma.plazoletas.infrastructure.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishStateRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.handler.impl.MenuDishHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class MenuDishControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuDishHandler menuDishHandler;
    private String token;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();

        token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OCwic3ViIjoi";
    }
    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    void saveMenuDishControllerTest() throws Exception {
        MenuDishRequestDto menuDishRequest = new MenuDishRequestDto("Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L);
        doNothing().when(menuDishHandler)
                .menuDishValidateAndSave(menuDishRequest, token);

        ResultActions response = mockMvc.perform(post("/restaurant/menudish")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishRequest)));

//        verify(menuDishHandler, times(1))
//                .menuDishValidateAndSave(menuDishRequest, token);

        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    void updateMenuDishControllerTest() throws Exception {
        MenuDishUpdateDto menuDishUpdateDto = new MenuDishUpdateDto(1L,2000,
                "Ensalada Con solo hortalizas");
        doNothing().when(menuDishHandler)
                .menuDishValidateAndUpdate(menuDishUpdateDto, token);

        ResultActions response = mockMvc.perform(put("/restaurant/menudish")
                        .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishUpdateDto)));

        //verify(menuDishHandler, times(1))
               // .menuDishValidateAndUpdate(menuDishUpdateDto, token);
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Cliente")
    void saveMenuDishControllerExceptionTest() throws Exception {
        MenuDishRequestDto menuDishRequest = new MenuDishRequestDto("Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L);
        doNothing().when(menuDishHandler)
                .menuDishValidateAndSave(menuDishRequest, token);

        ResultActions response = mockMvc.perform(post("/restaurant/menudish")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishRequest)));

        verify(menuDishHandler, never()).menuDishValidateAndSave(menuDishRequest, token);
        response.andDo(print())
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Cliente")
    void updateMenuDishControllerExceptionTest() throws Exception {
        MenuDishUpdateDto menuDishUpdateDto = new MenuDishUpdateDto(1L,2000,
                "Ensalada Con solo hortalizas");
        doNothing().when(menuDishHandler)
                .menuDishValidateAndUpdate(menuDishUpdateDto, token);

        ResultActions response = mockMvc.perform(put("/restaurant/menudish")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishUpdateDto)));

        verify(menuDishHandler, never())
                .menuDishValidateAndUpdate(menuDishUpdateDto, token);
        response.andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    void assignStatusMenuDishControllerTest() throws Exception {
        MenuDishStateRequestDto menuDishDto = new MenuDishStateRequestDto(1L,false);
        doNothing().when(menuDishHandler)
                .assignStatusMenuDish(menuDishDto, token);

        ResultActions response = mockMvc.perform(put("/restaurant/statemenudish")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishDto)));

        //verify(menuDishHandler, times(1))
        // .menuDishValidateAndUpdate(menuDishUpdateDto, token);
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Cliente")
    void assignStatusMenuDishControllerExceptionTest() throws Exception {
        MenuDishStateRequestDto menuDishDto = new MenuDishStateRequestDto(1L,false);
        doNothing().when(menuDishHandler)
                .assignStatusMenuDish(menuDishDto, token);

        ResultActions response = mockMvc.perform(put("/restaurant/statemenudish")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishDto)));

        verify(menuDishHandler, never()).assignStatusMenuDish(menuDishDto, token);
        response.andDo(print())
                .andExpect(status().isForbidden());
    }

}