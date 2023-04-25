package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByIdOrNit(Long id, String nit);
}
