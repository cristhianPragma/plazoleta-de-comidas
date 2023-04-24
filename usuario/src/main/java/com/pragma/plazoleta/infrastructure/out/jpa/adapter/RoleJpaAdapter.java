package com.pragma.plazoleta.infrastructure.out.jpa.adapter;
import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.domain.spi.IRolePersistencePort;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRoleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleEntityRepository roleEntityRepository;
    private final IRoleEntityMapper roleEntityMapper;
    @Override
    public Role findRoleById(Integer id) {
        RoleEntity roleEntity = roleEntityRepository.findById(id)
                .orElseThrow(()-> new RequestException("Rol no encontrado", HttpStatus.NOT_FOUND));
        return roleEntityMapper.toRole(roleEntity);
    }
}
