package com.pragma.plazoletas.domain.spi;


import com.pragma.plazoletas.domain.model.MenuDish;

public interface IMenuDishPersistentPort {
    void saveMenuDish(MenuDish menuDish);
}
