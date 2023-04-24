package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.User;

public interface IUserPersistencePort {
    User saveUser(User user);
    User findByUserId(Long id);
}
