package com.pragma.plazoletas.infrastructure.input;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.application.dto.response.OrderResponse;
import com.pragma.plazoletas.application.handler.IAssignedOrder;
import com.pragma.plazoletas.application.handler.IOrderHandler;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderHandler orderHandler;
    private final IAssignedOrder assignedOrder;
    @Operation(summary = "Agregar Pedido, validando pedidos del usuario y platos restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @PostMapping("/order")
    public ResponseEntity<Void> saveMenuDish(@RequestHeader("Authorization") String token,
                                             @RequestBody List<OrderDishRequestDto> orderDto) {
        orderHandler.SaverOrderHandler(orderDto, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Lista de pedidos por estado y empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de pedidos", content = @Content),
            @ApiResponse(responseCode = "400", description = "Campo ingresado de manera incorrecta", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @GetMapping("/order/{state}/{size}/{page}")
    public ResponseEntity<List<OrderResponse>> listOrder(@RequestHeader("Authorization") String token,
                                                         @PathVariable int state,
                                                         @PathVariable int size,
                                                         @PathVariable int page) {
        return new ResponseEntity<>(orderHandler.listOrderResponseHandler(state,size,page,token),
                HttpStatus.OK);
    }

    @Operation(summary = "Asignarse a un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado asignado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content)
    })
    @PutMapping("/order/{orderId}")
    public ResponseEntity<List<Void>> assignedOrder(@RequestHeader("Authorization") String token,
                                                         @PathVariable int orderId) {
        assignedOrder.assignedOrder(orderId, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
