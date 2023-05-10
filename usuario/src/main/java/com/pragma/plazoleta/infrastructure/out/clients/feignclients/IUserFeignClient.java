package com.pragma.plazoleta.infrastructure.out.clients.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "restaurant-service")
public interface IUserFeignClient {
    @GetMapping("/restaurant/employee/{user}/{restaurant}")
    void saveEmployee(@RequestHeader("Authorization") String token,
                     @PathVariable Long user, @PathVariable Long restaurant);

}
