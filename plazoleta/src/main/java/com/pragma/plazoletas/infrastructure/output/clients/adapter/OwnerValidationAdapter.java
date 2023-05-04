package com.pragma.plazoletas.infrastructure.output.clients.adapter;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import com.pragma.plazoletas.application.handler.IOwnerValidation;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.exceptionhandler.ExceptionFeignClient;
import com.pragma.plazoletas.infrastructure.output.clients.feignclients.IUserFeignClient;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerValidationAdapter implements IOwnerValidation {
    private final JwtService jwtService;
    @Override
    public void validation(Long id, String token) {
        final int OWNER_ROL_ID = 2;
        final String URL ="http://localhost:8081";
        RequestInterceptor requestInterceptor;
        String jwt = jwtService.parseJwt(token);
        if (!jwtService.isValidToken(jwt))
            throw new RequestException("Token invalido",
                    HttpStatus.BAD_REQUEST);

        requestInterceptor = template -> template.header("Authorization", token);

        Feign.Builder builder = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .requestInterceptor(requestInterceptor)
                .errorDecoder(new ExceptionFeignClient());

        IUserFeignClient userFeignClient = builder.target(IUserFeignClient.class, URL);
        RoleResponseDto roleResponseDto = userFeignClient.findByRoleId(id);
        if (roleResponseDto.getId() != OWNER_ROL_ID)
            throw  new RequestException("El usuario ingresado no tiene el rol, para esta acción",
                    HttpStatus.BAD_REQUEST);
    }
}
