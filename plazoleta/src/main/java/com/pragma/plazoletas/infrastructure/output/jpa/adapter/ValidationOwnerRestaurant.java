package com.pragma.plazoletas.infrastructure.output.jpa.adapter;
import com.pragma.plazoletas.application.handler.IValidateRestaurantOwnerId;
import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationOwnerRestaurant implements IValidateRestaurantOwnerId {

    private final JwtService jwtService;
    @Override
    public void validateRestaurantOwnerId(Restaurant restaurant, String token) {
        String jwt = jwtService.parseJwt(token);

        if (!jwtService.isValidToken(jwt))
            throw new RequestException("Token invalido",
                    HttpStatus.BAD_REQUEST);

        Long ownerId = jwtService.extractUserId(jwt);

        if (restaurant.getOwnerId() != ownerId)
            throw new RequestException("El restaurante no pertenece a este propietario",
                    HttpStatus.BAD_REQUEST);
    }
}
