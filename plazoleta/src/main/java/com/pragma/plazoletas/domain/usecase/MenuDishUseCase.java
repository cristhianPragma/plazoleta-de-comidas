package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.IMenuDishServicePort;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.spi.IMenuDishPersistentPort;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class MenuDishUseCase implements IMenuDishServicePort {
    private final IMenuDishPersistentPort menuDishPersistentPort;

    @Override
    public void saveMenuDish(MenuDish menuDish) {
        menuDishPersistentPort.saveMenuDish(menuDish);
    }
}