package com.unicartagena.ApiHexagonalV2.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para APIs REST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permitimos acceso público a auth y a la documentación Swagger
                        .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/api-docs/**", "/h2-console/**").permitAll()
                        // (Opcional) Aquí podrías exigir token para los CRUD de Países/Ciudades en el futuro
                        .requestMatchers("/api/v1/paises/**", "/api/v1/ciudades/**", "/api/v1/reportes/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}