package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRoleServicePort rolServicePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRoleServicePort roleServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolServicePort  = roleServicePort;
    }

    @Override
    public Long saveUser(User user,  int idAssignRole) {
        user.setRole(rolServicePort.findById(idAssignRole));
        return userPersistencePort.saveUser(user).getId();
    }

    @Override
    public int roleFindByUserId(Long id) {
        User user = userPersistencePort.findByUserId(id);
        return user.getRole().getId();
    }
}
