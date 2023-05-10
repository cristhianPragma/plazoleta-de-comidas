package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuDishRepository extends JpaRepository<MenuDishEntity,Long> {
    Page<MenuDishEntity> findByRestaurantOrderByCategory(RestaurantEntity restaurant, Pageable pageable);
}
