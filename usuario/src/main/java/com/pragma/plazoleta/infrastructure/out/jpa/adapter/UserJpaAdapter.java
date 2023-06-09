package com.pragma.plazoleta.infrastructure.out.jpa.adapter;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserEntityRepository userEntityRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public User saveUser(User user) {
        Optional<UserEntity> findUser = userEntityRepository
                .findByIdOrDocumentNumberOrEmail(user.getId(),user.getDocumentNumber(), user.getEmail());

        if (findUser.isPresent())
            throw new RequestException("El usuario ya existe", HttpStatus.BAD_REQUEST);

        UserEntity userEntity = userEntityRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public User findByUserId(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id)
                .orElseThrow(()-> new RequestException("Usuario no encontrado", HttpStatus.NOT_FOUND));
        return userEntityMapper.toUserModel(userEntity);
    }
}
