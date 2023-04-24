package com.pragma.plazoleta.infrastructure.configuration;

import com.pragma.plazoleta.application.handler.IPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeImpl implements IPasswordEncoder {
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return passwordEncoder().encode(password);
    }
}
