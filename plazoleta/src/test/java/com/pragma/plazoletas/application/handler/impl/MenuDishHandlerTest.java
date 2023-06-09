package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishStateRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.dto.response.MenuDishResponseDto;
import com.pragma.plazoletas.application.handler.IValidateRestaurantOwnerId;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IMenuDishRequestMapper;
import com.pragma.plazoletas.domain.api.IMenuDishServicePort;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuDishHandlerTest {
    @Mock
    private IMenuDishRequestMapper menuDishRequestMapper;
    @Mock
    private IMenuDishServicePort menuDishServicePort;
    @Mock
    private IValidationHandler validationHandler;
    @Mock
    private IRestaurantServicePort restaurantServicePort;
    @Mock
    private IValidateRestaurantOwnerId validateRestaurantOwnerId;
    @InjectMocks
    private MenuDishHandler menuDishHandler;
    private MenuDish menuDish;
    Long ownerId = 1L;
    Restaurant restaurant;
    Long restaurantId = 1L;
    @BeforeEach
    void SetUp(){
        menuDish = new MenuDish(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", new Category(1, "Ensalada", "Ensaladas"),1L, true);
        restaurant = new Restaurant();
    }
    @Test
    void menuDishSaveHandlerTest() {
        MenuDishRequestDto menuDishRequest = new MenuDishRequestDto("Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L);

        doNothing().when(validationHandler).validate(menuDishRequest);
        when(menuDishRequestMapper.toMenuDish(menuDishRequest)).thenReturn(menuDish);
        when(restaurantServicePort.findByRestaurantId(restaurantId)).thenReturn(restaurant);
        doNothing().when(validateRestaurantOwnerId).validateRestaurantOwnerId(restaurant, "ownerId");
        doNothing().when(menuDishServicePort).saveMenuDish(menuDish);

        menuDishHandler.menuDishValidateAndSave(menuDishRequest, "ownerId");

        verify(validationHandler, times(1)).validate(menuDishRequest);
        verify(menuDishRequestMapper, times(1)).toMenuDish(menuDishRequest);
        verify(validateRestaurantOwnerId, times(1))
                .validateRestaurantOwnerId(restaurant, "ownerId");
        verify(menuDishServicePort, times(1)).saveMenuDish(menuDish);
    }

    @Test
    void menuDishUpdateHandlerTest() {
        MenuDishUpdateDto menuDishUpdate = new MenuDishUpdateDto(1L, 1500,
                "Solo Hortalizas con aderezo");
        doNothing().when(validationHandler).validate(menuDishUpdate);
        when(menuDishServicePort.findByIdMenuDish(menuDishUpdate.getId())).thenReturn(menuDish);

        menuDishHandler.menuDishValidateAndUpdate(menuDishUpdate, "ownerId");

        assertEquals(1500, menuDish.getPrice());
        assertEquals("Solo Hortalizas con aderezo", menuDish.getDescription());
        verify(validationHandler, times(1)).validate(menuDishUpdate);
        verify(restaurantServicePort, times(1)).findByRestaurantId(restaurantId);

    }

    @Test
    void assignStatusMenuDishHandlerTest() {
        MenuDishStateRequestDto menuDishState = new MenuDishStateRequestDto(1L, false);
        doNothing().when(validationHandler).validate(menuDishState);
        when(menuDishServicePort.findByIdMenuDish(menuDishState.getId())).thenReturn(menuDish);

        menuDishHandler.assignStatusMenuDish(menuDishState, "ownerId");

        assertFalse(menuDish.isActive());
        verify(validationHandler, times(1)).validate(menuDishState);
        verify(restaurantServicePort, times(1)).findByRestaurantId(restaurantId);

    }
    @Test
    void listMenuDishResponseHandlerTest(){
        Long  restaurantId = 1L;
        int pageSize =1, pageNumber=1;
        List<MenuDish>menuDishList = List.of(new MenuDish());

        when(menuDishServicePort.listMenuDish(restaurantId, pageSize, pageNumber))
                .thenReturn(menuDishList);
        when(menuDishRequestMapper.toMenuDishResponseDto(menuDishList))
                .thenReturn(List.of(new MenuDishResponseDto()));

        List<MenuDishResponseDto>listResponseDto = menuDishHandler
                .listMenuDishResponse(restaurantId,pageSize, pageNumber);

        verify(menuDishServicePort, times(1)).listMenuDish(restaurantId, pageSize, pageNumber);
        verify(menuDishRequestMapper, times(1)).toMenuDishResponseDto(menuDishList);
        assertEquals(1, listResponseDto.size());
    }
}