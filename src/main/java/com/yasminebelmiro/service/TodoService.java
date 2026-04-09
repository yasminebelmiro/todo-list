package com.yasminebelmiro.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.yasminebelmiro.entity.Todo;
import com.yasminebelmiro.repository.TodoRepository;

public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return listOrdenedByPrioridade();
    }

    public List<Todo> listOrdenedByPrioridade() {
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Todo todo) {
        todoRepository.save(todo);
        return listOrdenedByPrioridade();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return listOrdenedByPrioridade();
    }
}
