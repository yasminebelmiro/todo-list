package com.yasminebelmiro.todo_list.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String name,
        @NotBlank(message = "O email do usuário é obrigatório")
        @Email(message = "O email do usuário deve ser um email válido") 
        String email,
        @NotBlank(message = "A senha do usuário é obrigatória")
        @Size(min = 6, max = 8, message = "A senha do usuário deve conter entre 6 e 8 caracteres")
        String password
) {
}