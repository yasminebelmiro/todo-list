package com.yasminebelmiro.todo_list.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.format("User com ID %d não foi encontrado no sistema.", id));
    }

    public UserNotFoundException(String message) {
        super(message);
    }
    
}
