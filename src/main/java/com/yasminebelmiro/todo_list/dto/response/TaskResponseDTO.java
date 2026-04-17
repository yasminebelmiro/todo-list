package com.yasminebelmiro.todo_list.dto.response;

import com.yasminebelmiro.todo_list.entity.Task;
import com.yasminebelmiro.todo_list.enums.PriorityEnum;

public record TaskResponseDTO(
        Long id,
        String name,
        String description,
        boolean completed,
       PriorityEnum priority,
        Long userId,
        Long taskListId) {
    public TaskResponseDTO(Task task) {
        this(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.isCompleted(),
                task.getPriority(),
                task.getUser().getId(),
                task.getTaskList().getId());
    }

}
