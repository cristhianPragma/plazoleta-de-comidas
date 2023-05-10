package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.User;

public interface IUserServicePort {
    Long saveUser(User user, int idAssignRole);
    int roleFindByUserId(Long id);

}
