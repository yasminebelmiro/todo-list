package com.yasminebelmiro.todo_list.dto.request;

import com.yasminebelmiro.todo_list.enums.PriorityEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record TaskRequestDTO(
        @NotBlank(message = "O nome da tarefa é obrigatório")
        @Size(min = 1, max = 100, message = "O nome da tarefa deve conter entre 3 e 100 caracteres")
        String name,
        String description,
        @NotNull(message = "A prioridade da tarefa é obrigatória")
       PriorityEnum priority,
        @Positive(message = "O ID do usuário deve ser um número positivo")
        Long userId,
        @Positive(message = "O ID da lista de tarefas deve ser um número positivo")
        Long taskListId) {

}
