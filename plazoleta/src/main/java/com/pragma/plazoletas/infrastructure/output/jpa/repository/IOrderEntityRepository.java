package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderEntityRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findFirstByCustomerIdOrderByIdDesc(Long customerId);
    Page<OrderEntity> findByStateEntityAndRestaurantEntity(StateEntity stateEntity,
                                                           RestaurantEntity restaurant, Pageable pageable);
}
