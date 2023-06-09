package com.pragma.plazoletas.infrastructure.input;

import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoletas.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletas.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoletas.application.handler.IEmployeeHandler;
import com.pragma.plazoletas.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantHandler restaurantHandler;
    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Agregar restaurante, validando rol propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Void> saveRestaurant(@RequestHeader("Authorization") String token,
                                               @RequestBody RestaurantRequestDto restaurant) {
        restaurantHandler.saveRestaurant(restaurant, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Listar restaurantes paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes", content = @Content),
            @ApiResponse(responseCode = "204", description = "Lista vacia", content = @Content)
    })
    @GetMapping("/restaurantList/{page}/{size}")
    public ResponseEntity<List<RestaurantResponseDto>> restaurantList(@PathVariable int page,
                                                                      @PathVariable int size) {
        return new ResponseEntity<>(restaurantHandler.restauranListResponseDtos(size, page), HttpStatus.OK);
    }

    @GetMapping("/employee/{user}/{restaurant}")
    public ResponseEntity<Void>saveEmployee(@RequestHeader("Authorization") String token,
                                            @PathVariable Long user, @PathVariable Long restaurant){
        employeeHandler.saveEmployeeHandler(new EmployeeRequestDto(restaurant,user),token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
