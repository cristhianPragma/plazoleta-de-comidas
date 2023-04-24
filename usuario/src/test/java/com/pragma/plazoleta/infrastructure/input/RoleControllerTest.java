package com.pragma.plazoleta.infrastructure.input;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.application.handler.IRoleHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(RoleController.class)
class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    IRoleHandler roleHandler;
    @MockBean
    IUserHandler userHandler;
    @Test
    void findByRoleIdControllerTest() throws Exception {
        Long roleIdRequest =1l;
        int roleIdResponse = 2;
        RoleResponseDto role = new RoleResponseDto(roleIdResponse, "propietario");

        when(userHandler.roleIdFindUserId(roleIdRequest)).thenReturn(roleIdResponse);
        when(roleHandler.findByRoleId(roleIdResponse)).thenReturn(role);

        ResultActions response = mockMvc.perform(get("/api/role/"+roleIdRequest));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(role.getId())))
                .andExpect(jsonPath("$.name", is(role.getName())));

    }
}