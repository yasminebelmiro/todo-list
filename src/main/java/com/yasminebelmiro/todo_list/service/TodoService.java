package com.yasminebelmiro.todo_list.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.entity.Todo;
import com.yasminebelmiro.todo_list.repository.TodoRepository;

@Service
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
