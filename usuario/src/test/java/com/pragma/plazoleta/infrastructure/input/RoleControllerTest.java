package com.pragma.plazoleta.infrastructure.input;
import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.application.handler.IRoleHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class RoleControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @MockBean
    IRoleHandler roleHandler;
    @MockBean
    IUserHandler userHandler;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "ana", password = "Ana1234*")
    void findByRoleIdControllerSuccessTest() throws Exception {
        Long roleIdRequest =1l;
        int roleIdResponse = 2;
        RoleResponseDto role = new RoleResponseDto(roleIdResponse, "propietario");

        when(userHandler.roleIdFindUserId(roleIdRequest)).thenReturn(roleIdResponse);
        when(roleHandler.findByRoleId(roleIdResponse)).thenReturn(role);

        ResultActions response = mockMvc.perform(get("/users/role/"+roleIdRequest));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(role.getId())))
                .andExpect(jsonPath("$.name", is(role.getName())));

    }
    @Test
    void findByRoleIdControllerForbiddenTest() throws Exception {
        Long roleIdRequest =1l;
        int roleIdResponse = 2;
        RoleResponseDto role = new RoleResponseDto(roleIdResponse, "propietario");

        when(userHandler.roleIdFindUserId(roleIdRequest)).thenReturn(roleIdResponse);
        when(roleHandler.findByRoleId(roleIdResponse)).thenReturn(role);

        ResultActions response = mockMvc.perform(get("/users/role/"+roleIdRequest));

        response.andExpect(status().isForbidden());
    }
}