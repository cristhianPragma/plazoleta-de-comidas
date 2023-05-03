package com.pragma.plazoletas.domain.spi;


import com.pragma.plazoletas.domain.model.MenuDish;

import java.util.List;

public interface IMenuDishPersistentPort {
    void saveMenuDish(MenuDish menuDish);
    MenuDish findByIdMenuDish(Long id);
}
