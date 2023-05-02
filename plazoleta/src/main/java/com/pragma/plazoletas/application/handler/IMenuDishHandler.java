package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.MenuDishRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishStateRequestDto;
import com.pragma.plazoletas.application.dto.request.MenuDishUpdateDto;
import com.pragma.plazoletas.domain.model.MenuDish;

public interface IMenuDishHandler {
    void menuDishValidateAndSave(MenuDishRequestDto menuDishRequestDto, String token);
    void menuDishValidateAndUpdate(MenuDishUpdateDto menuDishUpdateDto, String token);
    void assignStatusMenuDish(MenuDishStateRequestDto menuDish, String token);
}
