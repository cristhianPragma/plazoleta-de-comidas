package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IUserEntityRepositoryTest {
    @Autowired
    private IUserEntityRepository userEntityRepository;
    @Autowired
    private  IRoleEntityRepository roleEntityRepository;

    @BeforeEach
    void setUpStar(){
        if (roleEntityRepository.findAll().size() <=0)
            roleEntityRepository.save(new RoleEntity(1, "propietario",
                    "Gestionar restaurante"));
    }

    @Test
    void saveUserJpaTest(){
        UserEntity userEntity = new UserEntity(1l,"Carlos","1234567",
                "Dias", "6707941","carlos@gmail.com", "Addim1234+",
                new RoleEntity(1, "propietario","Gestionar restaurante"));

        UserEntity userSaved = userEntityRepository.save(userEntity);

        assertEquals(userEntity.getId(), userSaved.getId());
    }
}