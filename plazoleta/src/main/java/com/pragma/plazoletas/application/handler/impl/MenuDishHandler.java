package com.pragma.plazoletas.application.handler.impl;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.application.handler.IMenuDishHandler;
import com.pragma.plazoletas.application.handler.IValidateRestaurantOwnerId;
import com.pragma.plazoletas.application.handler.IValidationHandler;
import com.pragma.plazoletas.application.mapper.IMenuDishRequestMapper;
import com.pragma.plazoletas.domain.api.IMenuDishServicePort;
import com.pragma.plazoletas.domain.api.IRestaurantServicePort;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuDishHandler implements IMenuDishHandler {
    private final IMenuDishRequestMapper menuDishRequestMapper;
    private final IMenuDishServicePort menuDishServicePort;
    private final IValidationHandler validationHandler;
    private final IRestaurantServicePort restaurantServicePort;
    private final IValidateRestaurantOwnerId validateRestaurantOwnerId;

    @Override
    public void menuDishValidateAndSave(MenuDishRequestDto menuDishRequestDto, Long ownerId) {
        validationHandler.validate(menuDishRequestDto);
        menuDishSave(menuDishRequestDto.getRestaurantId(), ownerId,
                menuDishRequestMapper.toMenuDish(menuDishRequestDto));
    }
    @Override
    public void menuDishValidateAndUpdate(MenuDishUpdateDto menuDishUpdateDto, Long ownerId){
        validationHandler.validate(menuDishUpdateDto);
        MenuDish menuDish = menuDishServicePort.findByIdMenuDish(menuDishUpdateDto.getId());
        menuDish.setDescription(menuDishUpdateDto.getDescription());
        menuDish.setPrice(menuDishUpdateDto.getPrice());
        menuDishSave(menuDish.getRestaurantId(), ownerId, menuDish);
    }
    private void menuDishSave(Long menuDishRestaurantId, Long ownerId, MenuDish menuDish){
        Restaurant restaurant =  restaurantServicePort
                .findByRestaurantId(menuDishRestaurantId);
        validateRestaurantOwnerId.validateRestaurantOwnerId(restaurant, ownerId);
        menuDishServicePort.saveMenuDish(menuDish);
    }
}
