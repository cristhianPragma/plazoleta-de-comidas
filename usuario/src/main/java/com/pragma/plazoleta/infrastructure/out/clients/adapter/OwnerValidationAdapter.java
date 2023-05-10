package com.pragma.plazoleta.infrastructure.out.clients.adapter;
import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoleta.application.handler.ISaveEmployee;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.exceptionhandler.ExceptionFeignClient;
import com.pragma.plazoleta.infrastructure.out.clients.feignclients.IUserFeignClient;
import com.pragma.plazoleta.infrastructure.security.jwt.JwtService;
import feign.Feign;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerValidationAdapter implements ISaveEmployee {
    private final JwtService jwtService;
    @Override
    public void saveEmployeeRestaurant(EmployeeRequestDto employeeRequestDto, String token) {
        final String URL ="http://localhost:8082";
        RequestInterceptor requestInterceptor;
        String jwt = parseJwt(token);
        if (!jwtService.isValidToken(jwt))
            throw new RequestException("Token invalido",
                    HttpStatus.BAD_REQUEST);
        requestInterceptor = template -> template.header("Authorization", token);

        Feign.Builder builder = Feign.builder()
                .contract(new SpringMvcContract())
                .requestInterceptor(requestInterceptor)
                .errorDecoder(new ExceptionFeignClient());
        IUserFeignClient userFeignClient = builder.target(IUserFeignClient.class, URL);
        userFeignClient
                .saveEmployee(token, employeeRequestDto.getUserId(), employeeRequestDto.getRestaurantId());

    }
    private String parseJwt(String token){
        token = token.substring(7);
        return token;
    }

}
