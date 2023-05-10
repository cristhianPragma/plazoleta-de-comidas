package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MenuDishOrderListMapperAdapterTest {
    @Mock
    private IMenuDishRepository menuDishRepository;
    @Mock
    private IOrderDishMapper orderMapper;
    @InjectMocks
    MenuDishOrderListMapperAdapter menuDishOrderListMapperAdapter;
    @Test
    void toOrderDishMapperSuccessTest() {
        List<OrderDishRequestDto> orderDishRequestDtoList = List.of(new OrderDishRequestDto(1L,3),
                new OrderDishRequestDto(2L,4));
        List<OrderDishEntity>orderDishEntityList = new ArrayList<>();

        when(menuDishRepository.findById(1L)).thenReturn(Optional.of(new MenuDishEntity(1L, "Sopa",
                500, "sopa", "http://sopa.png", new CategoryMenuDishEntity(),
                new RestaurantEntity(1L,"Rest1","cra2","22555",
                        "http:logo.png","22255",1L),true)));
        when(menuDishRepository.findById(2L)).thenReturn(Optional.of(new MenuDishEntity(1L, "Arroz",
                1500, "Arroz", "http://arroz.png", new CategoryMenuDishEntity(),
                new RestaurantEntity(1L,"Rest1","cra2","22555",
                        "http:logo.png","22255",1L),true)));

        when(orderMapper.toOrderDishModel(any())).thenReturn(List.of(new OrderDish(), new OrderDish()));

        List<OrderDish> orderDishList= menuDishOrderListMapperAdapter.toOrderDishMapper(orderDishRequestDtoList);

        verify(menuDishRepository, times(1)).findById(1L);
        verify(menuDishRepository, times(1)).findById(2L);
        assertEquals(2, orderDishList.size());
    }

    @Test
    void toOrderDishMapperExceptionTest() {
        List<OrderDishRequestDto> orderDishRequestDtoList = List.of(new OrderDishRequestDto(1L,3),
                new OrderDishRequestDto(2L,4));
        List<OrderDishEntity>orderDishEntityList = new ArrayList<>();

        when(menuDishRepository.findById(1L)).thenReturn(Optional.of(new MenuDishEntity(1L, "Sopa",
                500, "sopa", "http://sopa.png", new CategoryMenuDishEntity(),
                new RestaurantEntity(1L,"Rest1","cra2","22555",
                        "http:logo.png","22255",1L),true)));
        when(menuDishRepository.findById(2L)).thenReturn(Optional.of(new MenuDishEntity(1L, "Arroz",
                1500, "Arroz", "http://arroz.png", new CategoryMenuDishEntity(),
                new RestaurantEntity(2L,"Rest2","cra4","2255555",
                        "http:logo2.png","5822255",1L),true)));

        RequestException exception = assertThrows(RequestException.class,
                ()->menuDishOrderListMapperAdapter.toOrderDishMapper(orderDishRequestDtoList));

        assertEquals("Los platos no pertenecen al mismo restaurante", exception.getMessage());
        verify(menuDishRepository, times(1)).findById(1L);
        verify(menuDishRepository, times(1)).findById(2L);
    }
}