package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    private int rolId;

    @BeforeEach
    void setUpStar(){
        rolId = roleEntityRepository.save(new RoleEntity(1, "propietario",
                    "Gestionar restaurante")).getId();
    }

    @Test
    void saveUserJpaTest(){
        UserEntity userEntity = new UserEntity(null,"Carlos","1234567",
                "Dias", "6707941","carlos@gmail.com", "Addim1234+",
                new RoleEntity(rolId, "propietario","Gestionar restaurante"));

        UserEntity userSaved = userEntityRepository.save(userEntity);

        assertEquals(userEntity.getName(), userSaved.getName());
        assertEquals(userEntity.getEmail(), userSaved.getEmail());
    }
    @ParameterizedTest
    @CsvSource({"1,1588222,falso@gmail.com", "7,1234567,error@gmail.com", "5,598555,carlos@gmail.com"})
    void findByIdOrDocumentOrEmailUserJpaTest(String id, String document, String email){
        UserEntity userEntity = new UserEntity(1l,"Carlos","1234567",
                "Dias", "6707941","carlos@gmail.com", "Addim1234+", null);

        userEntityRepository.save(userEntity);

        Optional<UserEntity> findUser = userEntityRepository
                .findByIdOrDocumentNumberOrEmail(Long.valueOf(id), document, email);

        assertTrue(findUser.isPresent());
    }
}