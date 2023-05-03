package com.pragma.plazoletas.infrastructure.output.clients.feignclients;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface IUserFeignClient {
    @GetMapping("/users/role/{userId}")
    RoleResponseDto findByRoleId(@PathVariable Long userId);

}
