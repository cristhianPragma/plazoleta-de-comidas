package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.User;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto, int idAssignRole);
    int roleIdFindUserId(Long userId);

}
