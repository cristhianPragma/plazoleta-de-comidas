package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.application.dto.response.AuthenticatedUserResponseDto;
import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserEntityMapperTest {
    @Autowired
    private IUserEntityMapper userEntityMapper;
    @Test
    void mapperToUserEntity() {
        User userModel = new User(1l,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+",
                new Role(1, "propietario","Gestionar restaurante"));

        UserEntity userEntity = userEntityMapper.toEntity(userModel);
        assertAll(
                ()->assertEquals(userModel.getId(), userEntity.getId()),
                ()->assertEquals(userModel.getName(), userEntity.getName()),
                ()->assertEquals(userModel.getDocumentNumber(), userEntity.getDocumentNumber()),
                ()->assertEquals(userModel.getLastName(), userEntity.getLastName()),
                ()->assertEquals(userModel.getPhone(), userEntity.getPhone()),
                ()->assertEquals(userModel.getEmail(), userEntity.getEmail()),
                ()->assertEquals(userModel.getPassword(), userEntity.getPassword()),
                ()->assertEquals(userModel.getRole().getId(), userEntity.getRoleEntity().getId())
        );
    }

    @Test
    void mapperToUserModel() {
        UserEntity userEntity = new UserEntity(1l,"Carlos","1234567",
                "Dias", "6707941","carlos@gmail.com", "Addim1234+",
                new RoleEntity(1, "propietario","Gestionar restaurante"));

        User userModel = userEntityMapper.toUserModel(userEntity);
        assertAll(
                ()->assertEquals(userEntity.getId(), userModel.getId()),
                ()->assertEquals(userEntity.getName(), userModel.getName()),
                ()->assertEquals(userEntity.getDocumentNumber(), userModel.getDocumentNumber()),
                ()->assertEquals(userEntity.getLastName(), userModel.getLastName()),
                ()->assertEquals(userEntity.getPhone(), userModel.getPhone()),
                ()->assertEquals(userEntity.getEmail(), userModel.getEmail()),
                ()->assertEquals(userEntity.getPassword(), userModel.getPassword()),
                ()->assertEquals(userEntity.getRoleEntity().getId(), userModel.getRole().getId())
        );
    }
    @Test
    void mapperToUserResponseAuth() {
        UserEntity userEntity = new UserEntity(1l,"Carlos","1234567",
                "Dias", "6707941","carlos@gmail.com", "Addim1234+",
                new RoleEntity(1, "propietario","Gestionar restaurante"));

        AuthenticatedUserResponseDto userAuth = userEntityMapper.toAuthenticatedUser(userEntity);
        assertAll(
                ()->assertEquals(userEntity.getId(), userAuth.getId()),
                ()->assertEquals(userEntity.getEmail(), userAuth.getEmail()),
                ()->assertEquals(userEntity.getPassword(), userAuth.getPassword()),
                ()->assertEquals(userEntity.getRoleEntity().getName(), userAuth.getRoleNameDto())
        );
    }
}