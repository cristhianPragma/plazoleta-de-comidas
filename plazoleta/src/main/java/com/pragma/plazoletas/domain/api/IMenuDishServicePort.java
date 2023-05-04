package com.pragma.plazoletas.domain.api;


import com.pragma.plazoletas.domain.model.MenuDish;

import java.util.List;


public interface IMenuDishServicePort {
    void saveMenuDish(MenuDish menuDish);
    MenuDish findByIdMenuDish(Long id);
    List<MenuDish>listMenuDish(int restaurantId, int pageSize, int pageNumber);
}
