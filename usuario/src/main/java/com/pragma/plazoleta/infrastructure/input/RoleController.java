package com.pragma.plazoleta.infrastructure.input;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.application.handler.IRoleHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleHandler roleHandler;
    private final IUserHandler userHandler;

    @Operation(summary = "Buscar rol por id de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sin resultados", content = @Content)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<RoleResponseDto> findByRoleId(@PathVariable Long userId) {
        RoleResponseDto roleResponseDto = roleHandler.findByRoleId(
                userHandler.roleIdFindUserId(userId));
        return new ResponseEntity<>(roleResponseDto, HttpStatus.OK);
    }
}