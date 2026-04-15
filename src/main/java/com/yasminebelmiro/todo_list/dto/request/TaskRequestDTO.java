package com.yasminebelmiro.todo_list.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank(message = "O nome da tarefa é obrigatório")
        @Size(min = 1, max = 100, message = "O nome da tarefa deve conter entre 3 e 100 caracteres")
        String nome,
        @Size(min = 1, max = 100, message = "A descrição da tarefa deve conter entre 3 e 100 caracteres")
        String descricao,
        @NotBlank(message = "O status da tarefa é obrigatório")
        boolean realizada,
        @NotBlank(message = "A prioridade da tarefa é obrigatória")
        @Positive(message = "A prioridade da tarefa deve ser um número positivo")
        @Size(min = 1, max = 100, message = "A prioridade da tarefa deve conter entre 1 e 100 caracteres")
        Integer prioridade,
        Long userId) {

}
