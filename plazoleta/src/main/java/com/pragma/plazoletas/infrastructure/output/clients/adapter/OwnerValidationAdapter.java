package com.pragma.plazoletas.infrastructure.output.clients.adapter;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import com.pragma.plazoletas.application.handler.IOwnerValidation;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.clients.feignclients.IUserFeignClietn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerValidationAdapter implements IOwnerValidation {
    private int OWNER_ROL_ID = 2;
    private final IUserFeignClietn userFeignClietn;

    @Override
    public void validation(Long id) {
        RoleResponseDto roleResponseDto = userFeignClietn.findByRoleId(id);
        if (roleResponseDto.getId() != OWNER_ROL_ID)
            throw  new RequestException("El usuario ingresado no tiene el rol, para esta acción",
                    HttpStatus.BAD_REQUEST);

    }
}
