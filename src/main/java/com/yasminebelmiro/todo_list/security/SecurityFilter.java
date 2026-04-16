package com.yasminebelmiro.todo_list.security;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yasminebelmiro.todo_list.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            var email = tokenService.validateToken(token);

            if (!email.isEmpty()) {
                // Se o token for válido, procuramos o utilizador na base de dados (o
                // UserRepository tem de ter um método de busca por email)
                UserDetails user = userRepository.findByEmail(email).orElse(null);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    // Guardamos a autenticação no contexto do Spring para esta requisição
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // Continua o fluxo da requisição (vai para o próximo filtro ou para o
        // Controller)
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        // O token JWT vem normalmente com o prefixo "Bearer ", por isso removemos essa
        // parte
        return authHeader.replace("Bearer ", "");
    }
}
