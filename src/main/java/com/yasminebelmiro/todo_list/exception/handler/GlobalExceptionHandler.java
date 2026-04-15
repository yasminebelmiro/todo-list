package com.yasminebelmiro.todo_list.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yasminebelmiro.todo_list.exception.ExceptionResponse;
import com.yasminebelmiro.todo_list.exception.TaskNotFoundException;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleTaskNotFound(TaskNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
