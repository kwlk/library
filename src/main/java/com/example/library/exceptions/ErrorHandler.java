package com.example.library.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Error error =
                new Error(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, error, headers, error.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorName = ex.getParameterName() + " parameter is missing";

        Error error =
                new Error(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorName);
        return new ResponseEntity<>(
                error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String errorName =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        Error error =
                new Error(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorName);
        return new ResponseEntity<>(
                error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler({ DataException.class })
    public ResponseEntity<Object> handleDataException(
            DataException ex, WebRequest request) {
        String errorName = "Data Exception";

        Error error =
                new Error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errorName);
        return new ResponseEntity<>(
                error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    public ResponseEntity<Object> handleUsernameNotFound(UsernameNotFoundException ex) {
        String errorName =
                "Error: " + ex.getMessage();

        Error error =
                new Error(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), errorName);
        return new ResponseEntity<>(
                error, new HttpHeaders(), error.getStatus());
    }
}
