package com.yasminebelmiro.todo_list.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasminebelmiro.todo_list.dto.response.StatusTaskResponseDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskResponseDTO;
import com.yasminebelmiro.todo_list.entity.Task;
import com.yasminebelmiro.todo_list.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService todoService;

    public TaskController(TaskService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/{userId}")
    ResponseEntity<TaskResponseDTO> create(@PathVariable Long userId, @RequestBody Task todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todo, userId));
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<TaskResponseDTO>> listByUserIdOrdenedByPrioridade(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.listByUserIdOrdenedByPrioridade(userId));
    }

    @PutMapping("/{id}")
    ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody Task todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TaskResponseDTO> delete(@PathVariable("id") Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    ResponseEntity<StatusTaskResponseDTO> atualizarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.atualizarStatus(id));
    }

    //listar por realizadas //listar não realizadas
}
