package com.yasminebelmiro.todo_list.dto.request;

public record TaskRequestDTO(Long id, String nome, String descricao, boolean realizada, Integer prioridade, Long userId) {

}
