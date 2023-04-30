package com.pragma.plazoleta.infrastructure.security.config;

import com.pragma.plazoleta.infrastructure.security.jwt.JwtAuthEntryPoint;
import com.pragma.plazoleta.infrastructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthEntryPoint authEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                .authorizeHttpRequests()
                .requestMatchers("/api/user/login", "/api/user/customer").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/user/administrator")
                .hasAnyAuthority("Administrador").and()
                .authorizeHttpRequests().requestMatchers("/api/user/owner")
                .hasAnyAuthority("Propietario")
                .anyRequest()
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
