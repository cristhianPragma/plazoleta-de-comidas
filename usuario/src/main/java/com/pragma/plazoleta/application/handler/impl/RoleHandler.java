package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.application.handler.IRoleHandler;
import com.pragma.plazoleta.application.mapper.IRoleResponseMapper;
import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleHandler implements IRoleHandler {
    private final IRoleServicePort roleServicePort;
    private final IRoleResponseMapper roleResponseMapper;
    @Override
    public RoleResponseDto findByRoleId(int roleId) {
        Role role = roleServicePort.findById(roleId);
        RoleResponseDto roleResponseDto = roleResponseMapper.toRoleResponse(role);
        return roleResponseDto;
    }
}
