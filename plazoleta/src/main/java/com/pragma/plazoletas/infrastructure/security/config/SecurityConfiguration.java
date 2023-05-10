package com.pragma.plazoletas.infrastructure.security.config;

import com.pragma.plazoletas.infrastructure.security.jwt.JwtAuthEntryPoint;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final JwtAuthEntryPoint authEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable().cors().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**","/v3/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/restaurant/create")
                .hasAnyAuthority("Administrador")
                .and()
                .authorizeHttpRequests().requestMatchers("/restaurant/menudish",
                        "/restaurant/statemenudish", "restaurant/employee")
                .hasAnyAuthority("Propietario")
                .and()
                .authorizeHttpRequests().requestMatchers("restaurant/order")
                .hasAnyAuthority("Cliente")
                .and()
                .authorizeHttpRequests().requestMatchers("/order/{state}/{size}/{page}", "/order/{orderId}")
                .hasAnyAuthority("Empleado")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
