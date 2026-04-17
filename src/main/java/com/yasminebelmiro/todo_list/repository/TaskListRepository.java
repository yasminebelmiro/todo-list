package com.yasminebelmiro.todo_list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasminebelmiro.todo_list.entity.TaskList;
import com.yasminebelmiro.todo_list.exception.TaskNotFoundException;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    default TaskList findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    List<TaskList> findByUserId(Long userId);
}
