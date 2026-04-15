package com.yasminebelmiro.todo_list.dto.response;

import com.yasminebelmiro.todo_list.entity.Task;

public record TaskResponseDTO(
        Long id,
        String nome,
        String descricao,
        boolean realizada,
        Integer prioridade,
        Long userId) {
    public TaskResponseDTO(Task task) {
        this(
                task.getId(),
                task.getNome(),
                task.getDescricao(),
                task.isRealizada(),
                task.getPrioridade(),
                task.getUser().getId());
    }
}
