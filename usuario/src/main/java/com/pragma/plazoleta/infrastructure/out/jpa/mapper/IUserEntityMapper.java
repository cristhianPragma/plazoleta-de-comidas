package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    @Mapping(source = "user.role", target = "roleEntity")
    UserEntity toEntity(User user);
    @Mapping(source ="userEntity.roleEntity" , target ="role")
    User toUserModel(UserEntity userEntity);

}
