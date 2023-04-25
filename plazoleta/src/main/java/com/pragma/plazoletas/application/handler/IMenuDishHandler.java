package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;

public interface IMenuDishHandler {
    void menuDishValidateAndSave(MenuDishRequestDto menuDishRequestDto, Long ownerId);
     void menuDishValidateAndUpdate(MenuDishUpdateDto menuDishUpdateDto, Long ownerId);
}
