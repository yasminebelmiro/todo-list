package com.yasminebelmiro.todo_list.exception;

public class TaskListNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskListNotFoundException(Long id) {
        super(String.format("TaskList com ID %d não foi encontrado no sistema.", id));
    }

    public TaskListNotFoundException(String message) {
        super(message);
    }

}

