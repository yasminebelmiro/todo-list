package com.yasminebelmiro.todo_list.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.yasminebelmiro.todo_list.entity.Todo;
import com.yasminebelmiro.todo_list.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);

    }

    @GetMapping
    List<Todo> listOrdenedByPrioridade() {
        return todoService.listOrdenedByPrioridade();
    }

    @PutMapping
    List<Todo> updade(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    List<Todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}
