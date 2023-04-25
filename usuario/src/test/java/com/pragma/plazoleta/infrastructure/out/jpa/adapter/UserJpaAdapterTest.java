package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IUserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {
    @Mock
    private IUserEntityRepository userEntityRepository;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private UserEntity userEntity;
    private User userSave;
    private final Long USER_ID = 1L;
    private User newUser;
    @BeforeEach
    void setUserEntity(){
        newUser = new User(USER_ID,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+", null);
    }
    @Test
    void saveUserJpaSuccessTest() {
         when(userEntityMapper.toEntity(newUser)).thenReturn(
                userEntity = new UserEntity(USER_ID,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+", null));

        when(userEntityRepository.save(any(UserEntity.class))).thenAnswer(
                (userEntitySave)->{return userEntitySave.getArgument(0);}
        );
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(
                new User(USER_ID,"Carlos","1234567", "Dias",
                        "6707941","carlos@gmail.com", "Addim1234+", null));
        userSave = userJpaAdapter.saveUser(newUser);

        assertEquals(USER_ID,userEntity.getId());
        assertEquals(USER_ID,userSave.getId());

    }
    @Test
    void saveUserJpaExceptionTest() {

        User existingUser = new User(USER_ID,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+", null);

        when(userEntityRepository.findByIdOrDocumentNumberOrEmail(USER_ID,existingUser.getDocumentNumber(),
                existingUser.getEmail())).thenReturn(Optional.of(new UserEntity(USER_ID,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+", null)));

        RequestException exception = assertThrows(RequestException.class,
                ()->userJpaAdapter.saveUser(newUser));

        assertEquals("El usuario ya existe", exception.getMessage());

        verify(userEntityMapper, never()).toEntity(any(User.class));
        verify(userEntityRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void findByUserIdTest(){
        when(userEntityRepository.findById(USER_ID)).thenReturn(Optional.of(
                userEntity = new UserEntity(USER_ID,"Carlos","1234567", "Dias",
                "6707941","carlos@gmail.com", "Addim1234+", null)
        ));
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(newUser);

         User user = userJpaAdapter.findByUserId(USER_ID);

         assertEquals(newUser.getId(), user.getId());
         assertEquals(newUser.getName(), user.getName());
    }
}