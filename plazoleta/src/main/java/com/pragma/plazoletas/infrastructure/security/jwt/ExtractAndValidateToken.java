package com.pragma.plazoletas.infrastructure.security.jwt;

import com.pragma.plazoletas.infrastructure.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtractAndValidateToken implements IExtractAndValidateToken{
    private final JwtService jwtService;
    public Long extract(String token){
        String jwt = parseJwt(token);
        if (!jwtService.isValidToken(jwt))
            throw new RequestException("Token invalido", HttpStatus.FORBIDDEN);

        return  jwtService.extractUserId(jwt);
    }
    private String parseJwt(String token){
        token = token.substring(7);
        return token;
    }
}
