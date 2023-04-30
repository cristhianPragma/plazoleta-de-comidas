package com.pragma.plazoleta.infrastructure.security.config;

import com.pragma.plazoleta.infrastructure.configuration.PasswordEncodeImpl;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUserEntityRepository userEntityRepository;
    private final IUserEntityMapper userEntityToResponse;
    private final PasswordEncodeImpl passwordEncoder;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userEntityToResponse.toAuthenticatedUser(userEntityRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado")));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
