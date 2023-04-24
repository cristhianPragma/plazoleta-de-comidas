package com.pragma.plazoleta.infrastructure.input;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserHandler userHandler;
    private int idAsignRole = 4;
    public UserController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }
    
    @Operation(summary = "Agregar usuario con rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content)
    })
    @PostMapping("/administrator")
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto) {
        idAsignRole = 2;
        userHandler.saveUser(userRequestDto, idAsignRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Se espera realizar diferentes endpoints. O if según la autenticación
}
