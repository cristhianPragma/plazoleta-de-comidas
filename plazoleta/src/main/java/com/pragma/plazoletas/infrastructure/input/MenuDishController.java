package com.pragma.plazoletas.infrastructure.input;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishStateRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.handler.IMenuDishHandler;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishCategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class MenuDishController {
    private final IMenuDishHandler menuDishHandler;
    private final IMenuDishCategoryRepository categoryRepository;

    public MenuDishController(IMenuDishHandler menuDishHandler, IMenuDishCategoryRepository categoryRepository) {
        this.menuDishHandler = menuDishHandler;
        this.categoryRepository = categoryRepository;
    }

    @Operation(summary = "Agregar Plato, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plato creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @PostMapping("/menudish")
    public ResponseEntity<Void> saveMenuDish(@RequestHeader("Authorization") String token,
                                             @RequestBody MenuDishRequestDto menuDishRequestDto) {
        menuDishHandler.menuDishValidateAndSave(menuDishRequestDto, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar Plato, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plato actualizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @PutMapping("/menudish")
    public ResponseEntity<Void> UpdateMenuDish(@RequestHeader("Authorization") String token,
                                               @RequestBody MenuDishUpdateDto menuDishUpdateDto) {
        menuDishHandler.menuDishValidateAndUpdate(menuDishUpdateDto, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Asignar estado a Plato, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado modificado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @PutMapping("/statemenudish")
    public ResponseEntity<Void> assignStatusMenuDish(@RequestHeader("Authorization") String token,
                                                     @RequestBody MenuDishStateRequestDto menuDishDto) {
        menuDishHandler.assignStatusMenuDish(menuDishDto, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/prueba2")
    public String prueba2(){
        System.out.println(categoryRepository.findAll());
        return "Probando puesto sin seguridad plato";
    }
}
