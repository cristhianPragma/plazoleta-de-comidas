package com.pragma.plazoletas.infrastructure.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.handler.impl.MenuDishHandler;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuDishController.class)
class MenuDishControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuDishHandler menuDishHandler;
    @Test
    void saveMenuDishControllerTest() throws Exception {
        MenuDishRequestDto menuDishRequest = new MenuDishRequestDto("Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L);
        doNothing().when(menuDishHandler)
                .menuDishValidateAndSave(menuDishRequest, 1L);

        ResultActions response = mockMvc.perform(post("/api/restaurant/menudish")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishRequest)));

        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateMenuDishControllerTest() throws Exception {
        MenuDishUpdateDto menuDishUpdateDto = new MenuDishUpdateDto(1L,2000,
                "Ensalada Con solo hortalizas");
        doNothing().when(menuDishHandler)
                .menuDishValidateAndUpdate(menuDishUpdateDto, 1L);

        ResultActions response = mockMvc.perform(put("/api/restaurant/menudish")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuDishUpdateDto)));

        response.andDo(print())
                .andExpect(status().isOk());
    }
}