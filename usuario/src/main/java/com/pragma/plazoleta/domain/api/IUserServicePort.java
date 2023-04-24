package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user, int idAssignRole);
    int roleFindByUserId(Long id);
}
