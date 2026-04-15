package com.yasminebelmiro.todo_list.exception.handler;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yasminebelmiro.todo_list.exception.ExceptionResponse;
import com.yasminebelmiro.todo_list.exception.TaskNotFoundException;
import com.yasminebelmiro.todo_list.exception.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> listaDeErros = ex.getBindingResult().getFieldErrors().stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .toList();
        String detalhesErrString = String.join("\n ", listaDeErros);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Erro de validação: " + listaDeErros.size() + " campo(s) inválido(s)", detalhesErrString);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
        public final ResponseEntity<ExceptionResponse> handleConstraintViolation(
                        ConstraintViolationException ex, WebRequest request) {
                String mensagemLimpa = ex.getMessage();
                // Se você quiser limpar o nome do método e deixar só a mensagem:
                if (mensagemLimpa.contains(": ")) {
                        mensagemLimpa = mensagemLimpa.split(": ")[1];
                }
                ExceptionResponse exceptionResponse = new ExceptionResponse(
                                new Date(),
                                "Erro de validação no parâmetro",
                                mensagemLimpa);
                return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleTaskNotFound(TaskNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
