package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuDishRepository extends JpaRepository<MenuDishEntity,Long> {
}
