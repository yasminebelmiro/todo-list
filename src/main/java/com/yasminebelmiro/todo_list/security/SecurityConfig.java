package com.yasminebelmiro.todo_list.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desativamos o CSRF porque vamos usar JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // A nossa API é Stateless (não guarda sessão)
                .authorizeHttpRequests(authorize -> authorize
                        // Quais rotas são públicas? (Qualquer pessoa pode aceder)
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Rota para fazer login
                        .requestMatchers(HttpMethod.POST, "/users").permitAll() // Rota para criar um novo utilizador
                        // Quaisquer outras rotas requerem autenticação (precisam do Token)
                        .anyRequest().authenticated()
                )
                // Adicionamos o nosso filtro antes do filtro padrão do Spring
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Encriptador de passwords (para não guardarmos passwords em texto limpo na base de dados)
        return new BCryptPasswordEncoder();
    }
}