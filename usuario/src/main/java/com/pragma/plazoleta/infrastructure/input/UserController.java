package com.pragma.plazoleta.infrastructure.input;

import com.pragma.plazoleta.application.dto.request.LoginRequest;
import com.pragma.plazoleta.application.dto.request.UserEmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.JwtResponse;
import com.pragma.plazoleta.application.handler.IUserHandler;
import com.pragma.plazoleta.infrastructure.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;
    private final AuthService authenticate;
    private int idAssignRole;

    @Operation(summary = "Inicio de sesión")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acceso denegado", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticate.authenticate(request));
    }

    @Operation(summary = "Agregar usuario con rol cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content)
    })
    @PostMapping("/customer")
    public ResponseEntity<Void> saveUserCustomer(@RequestBody UserRequestDto userRequestDto) {
        idAssignRole = 4;
        userHandler.saveUser(userRequestDto, idAssignRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @Operation(summary = "Agregar usuario con rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acceso denegado", content = @Content)
    })
    @PostMapping("/administrator")
    public ResponseEntity<Void> saveUserOwner(@RequestBody UserRequestDto userRequestDto) {
        idAssignRole = 2;
        userHandler.saveUser(userRequestDto, idAssignRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Agregar usuario con rol empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "401    ", description = "Acceso denegado", content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> saveUserEmployee(@RequestHeader("Authorization") String token,
                                                 @RequestBody UserEmployeeRequestDto employeeRequestDto) {
        idAssignRole = 3;
        userHandler.saveEmployee(employeeRequestDto, idAssignRole, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
