package com.yasminebelmiro.todo_list.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasminebelmiro.todo_list.dto.request.LoginRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.LoginResponseDTO;
import com.yasminebelmiro.todo_list.entity.User;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        // Cria um token provisório com os dados que vieram do Postman
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        
        // O AuthenticationManager vai automaticamente chamar o AuthorizationService (Passo 7) e validar a password
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        // Se a password estiver certa, geramos o token JWT
        var token = tokenService.generateToken((User) auth.getPrincipal());
        
        // Devolvemos o token na resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
