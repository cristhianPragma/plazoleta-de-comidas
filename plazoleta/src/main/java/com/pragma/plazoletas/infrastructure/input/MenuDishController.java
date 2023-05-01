package com.pragma.plazoletas.infrastructure.input;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.handler.IMenuDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class MenuDishController {
    private final IMenuDishHandler menuDishHandler;

    public MenuDishController(IMenuDishHandler menuDishHandler) {
        this.menuDishHandler = menuDishHandler;
    }


    @Operation(summary = "Agregar Plato, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plato creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content)
    })
    @PostMapping("/menudish")
    public ResponseEntity<Void> saveMenuDish(@RequestBody MenuDishRequestDto menuDishRequestDto) {
        menuDishHandler.menuDishValidateAndSave(menuDishRequestDto, 3L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar Plato, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plato actualizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content)
    })
    @PutMapping("/menudish")
    public ResponseEntity<Void> UpdateMenuDish(@RequestBody MenuDishUpdateDto menuDishUpdateDto) {
        menuDishHandler.menuDishValidateAndUpdate(menuDishUpdateDto, 3L);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
