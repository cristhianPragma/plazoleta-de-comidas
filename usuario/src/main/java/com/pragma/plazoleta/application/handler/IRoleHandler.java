package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;

public interface IRoleHandler {
    public RoleResponseDto findByRoleId(int roleId);
}
