package com.pragma.plazoleta.domain.usecase;
import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.model.Role;
import com.pragma.plazoleta.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role findById(Integer id) {
        return rolePersistencePort.findRoleById(id);
    }
}
