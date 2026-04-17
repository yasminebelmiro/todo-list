package com.yasminebelmiro.todo_list.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasminebelmiro.todo_list.dto.request.TaskListRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskListResponseDTO;
import com.yasminebelmiro.todo_list.service.TaskListService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/task-lists")
@Validated
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping("/user-{userId}")
    ResponseEntity<TaskListResponseDTO> create(@PathVariable Long userId, @Valid @RequestBody TaskListRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListService.create(dto, userId));
    }

    @GetMapping("/user-{userId}")
    ResponseEntity<List<TaskListResponseDTO>> listByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskListService.listByUserId(userId));
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskListResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<TaskListResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TaskListRequestDTO dto) {
        return ResponseEntity.ok(taskListService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        taskListService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
