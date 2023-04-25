package com.pragma.plazoletas.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.handler.IRestaurantHandler;
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

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IRestaurantHandler restaurantHandler;
    @Test
    void saveRestaurantControllerTest() throws Exception {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l);
        doNothing().when(restaurantHandler)
                .saveRestaurant(restaurantRequestDto);

        ResultActions response = mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequestDto)));

        verify(restaurantHandler, times(1)).saveRestaurant(restaurantRequestDto);
        response.andDo(print())
                .andExpect(status().isCreated());
    }
}