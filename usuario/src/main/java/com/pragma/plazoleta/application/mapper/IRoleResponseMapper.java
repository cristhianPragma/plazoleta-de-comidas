package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import com.pragma.plazoleta.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    RoleResponseDto toRoleResponse(Role role);
}
