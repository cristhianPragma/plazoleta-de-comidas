package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserEmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IPasswordEncoder;
import com.pragma.plazoleta.application.handler.ISaveEmployee;
import com.pragma.plazoleta.application.handler.IUserHandler;
import com.pragma.plazoleta.application.handler.IValidationHandler;
import com.pragma.plazoleta.application.mapper.IUserRequestMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper  userRequestMapper;
    private final IPasswordEncoder passwordEncoder;
    private final IValidationHandler validationHandler;
    private final ISaveEmployee saveEmployee;
    @Override
    public Long saveUser(UserRequestDto userRequestDto, int idAssignRole) {
        validationHandler.validate(userRequestDto);
        User user = userRequestMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServicePort.saveUser(user,idAssignRole);
    }
    @Transactional
    @Override
    public void saveEmployee(UserEmployeeRequestDto userEmployeeRequestDto, int idAssignRole, String token){
        validationHandler.validate(userEmployeeRequestDto);
        UserRequestDto userRequestDto = userRequestMapper.toUserDto(userEmployeeRequestDto);
        Long employeeId = saveUser(userRequestDto, idAssignRole);
        EmployeeRequestDto employeeRequestDto =
                new EmployeeRequestDto(userEmployeeRequestDto.getRestaurantId(), employeeId);
        saveEmployee.saveEmployeeRestaurant(employeeRequestDto, token);
    }
    @Override
    public int roleIdFindUserId(Long userId) {
        return userServicePort.roleFindByUserId(userId);
    }
}
