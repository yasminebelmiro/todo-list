package com.yasminebelmiro.todo_list.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @Email(message = "O email do usuário deve ser um email válido")
    String email,
    @NotBlank(message = "A senha do usuário é obrigatória")
     String password) {
}
