package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNameOrDocumentNumberOrEmail(String name, String indentDoc, String email);
}