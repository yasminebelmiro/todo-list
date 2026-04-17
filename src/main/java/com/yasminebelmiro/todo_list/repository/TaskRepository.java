package com.yasminebelmiro.todo_list.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yasminebelmiro.todo_list.entity.Task;
import com.yasminebelmiro.todo_list.exception.TaskNotFoundException;

public interface TaskRepository extends JpaRepository<Task, Long> {
    default Task findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

     List<Task> findByUserId(Long userId, Sort sort);

     List<Task> findByTaskListId(Long listId);
}
