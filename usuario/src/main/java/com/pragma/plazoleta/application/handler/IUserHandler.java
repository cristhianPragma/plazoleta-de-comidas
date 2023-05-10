package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.UserEmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;

public interface IUserHandler {
    Long saveUser(UserRequestDto userRequestDto, int idAssignRole);
    int roleIdFindUserId(Long userId);
    void saveEmployee(UserEmployeeRequestDto userEmployeeRequestDto, int idAssignRole, String token);

}
