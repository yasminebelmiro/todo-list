package com.yasminebelmiro.todo_list.exception;

public class TaskNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id) {
        super(String.format("Task com ID %d não foi encontrado no sistema.", id));
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
