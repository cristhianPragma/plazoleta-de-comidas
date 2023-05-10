package com.pragma.plazoletas.infrastructure.output.jpa.repository;

import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateEntityRepository extends JpaRepository<StateEntity, Integer> {
}
