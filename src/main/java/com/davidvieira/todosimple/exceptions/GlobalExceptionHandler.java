package com.davidvieira.todosimple.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.davidvieira.todosimple.services.exceptions.DataBindingViolationException;
import com.davidvieira.todosimple.services.exceptions.ObjectNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestError> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        final String message = "could not execute statement. Duplicate entry.";
        RestError restError = new RestError(HttpStatus
        .CONFLICT, message);
        return ResponseEntity.status(HttpStatus
        .CONFLICT).body(restError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        RestError restError = new RestError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(restError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception, WebRequest request) {
        RestError restError = new RestError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restError);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestError restError = new RestError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return ResponseEntity.unprocessableEntity().body(restError);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        final String errorMessage = "Unknown error occurred";
        RestError restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.internalServerError().body(restError);
    }


    @ExceptionHandler(DataBindingViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataBindingViolationException(DataBindingViolationException exception, WebRequest request) {
        RestError restError = new RestError(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restError);
    }
}
