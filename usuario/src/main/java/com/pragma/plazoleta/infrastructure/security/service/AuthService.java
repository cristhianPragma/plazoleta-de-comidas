package com.pragma.plazoleta.infrastructure.security.service;


import com.pragma.plazoleta.application.dto.request.LoginRequest;
import com.pragma.plazoleta.application.dto.response.AuthenticatedUserResponseDto;
import com.pragma.plazoleta.application.dto.response.JwtResponse;
import com.pragma.plazoleta.infrastructure.exception.RequestException;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IUserEntityRepository;
import com.pragma.plazoleta.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserEntityRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserEntityMapper userEntityMapper;

    public JwtResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        AuthenticatedUserResponseDto responseDto = userEntityMapper
                .toAuthenticatedUser(userRepository.findByEmail(request.getEmail())
                        .orElseThrow(()->new RequestException("Usuario no encontrado", HttpStatus.NOT_FOUND)));
        Map<String, Object> claims= new HashMap<>();
        claims.put("id", responseDto.getId());
        return new JwtResponse(jwtService.generateToken(claims, responseDto));
    }
}
