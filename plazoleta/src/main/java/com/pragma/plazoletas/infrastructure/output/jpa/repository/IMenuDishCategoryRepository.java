package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuDishCategoryRepository extends JpaRepository<CategoryMenuDishEntity,Integer> {
}
