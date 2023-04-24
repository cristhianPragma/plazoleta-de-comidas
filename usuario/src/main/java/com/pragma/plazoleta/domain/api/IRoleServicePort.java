package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Role;

public interface IRoleServicePort {
    Role findById(Integer id);
}
