package com.pragma.plazoletas.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoletas.application.handler.IEmployeeHandler;
import com.pragma.plazoletas.application.handler.IRestaurantHandler;
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
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class RestaurantControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IRestaurantHandler restaurantHandler;
    @MockBean
    private IEmployeeHandler employeeHandler;
    private final String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJQcm9waW";

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Administrador")
    void saveRestaurantControllerSuccessTest() throws Exception {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l);
        doNothing().when(restaurantHandler)
                .saveRestaurant(restaurantRequestDto, token);

        ResultActions response = mockMvc.perform(post("/restaurant/create")
                .header("Authorization", "Bearer " + token.substring(7))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequestDto)));
        verify(restaurantHandler, times(1)).saveRestaurant(restaurantRequestDto,token);
        response.andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    void saveRestaurantControllerExceptionTest() throws Exception {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("restaurante 1",
                "cra 1 N 162", "1255666",
                "http://img.png","1125555", 1l);
        doNothing().when(restaurantHandler)
                .saveRestaurant(restaurantRequestDto,token);

        ResultActions response = mockMvc.perform(post("/restaurant/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequestDto)));

        verify(restaurantHandler, never()).saveRestaurant(restaurantRequestDto, token);
        response.andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Cliente")
    void ListRestaurantControllerSuccessTest() throws Exception {
        int pageSize =2,pageNumber=0;
        List<RestaurantResponseDto>responseListDto =
                List.of(new RestaurantResponseDto(), new RestaurantResponseDto());

        when(restaurantHandler.restauranListResponseDtos(pageSize,pageNumber))
              .thenReturn(responseListDto);

        ResultActions response = mockMvc
                .perform(get("/restaurant/restaurantList/"+pageNumber+"/"+pageSize));

        verify(restaurantHandler, times(1))
                .restauranListResponseDtos(pageSize, pageNumber);
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(responseListDto.size())));
    }
    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Propietario")
    void saveEmployeeSuccessTest() throws Exception {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1L,1L);
        doNothing().when(employeeHandler)
                .saveEmployeeHandler(employeeRequestDto,token);

        ResultActions response = mockMvc
                .perform(get("/restaurant/employee/1/1")
                        .header("Authorization", "Bearer " + token.substring(7)));

        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*", authorities = "Cliente")
    void saveEmployeeExceptionTest() throws Exception {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1L,1L);
        doNothing().when(employeeHandler)
                .saveEmployeeHandler(employeeRequestDto,token);

        ResultActions response = mockMvc
                .perform(post("/restaurant/employee")
                        .header("Authorization", "Bearer " + token.substring(7))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestDto)));

        response.andDo(print())
                .andExpect(status().isForbidden());
    }
}