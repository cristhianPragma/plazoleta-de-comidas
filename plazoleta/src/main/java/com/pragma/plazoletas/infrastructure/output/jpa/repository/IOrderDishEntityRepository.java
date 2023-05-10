package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishEntityRepository extends JpaRepository<OrderDishEntity, Integer> {
}
