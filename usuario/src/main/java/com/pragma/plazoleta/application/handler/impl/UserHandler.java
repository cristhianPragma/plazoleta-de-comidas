package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IPasswordEncoder;
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
    @Override
    public void saveUser(UserRequestDto userRequestDto, int idAssignRole) {
        validationHandler.validate(userRequestDto);
        User user = userRequestMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userServicePort.saveUser(user,idAssignRole);
    }

    @Override
    public int roleIdFindUserId(Long userId) {
        return userServicePort.roleFindByUserId(userId);
    }
}
