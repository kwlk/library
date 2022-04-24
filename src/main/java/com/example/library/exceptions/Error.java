package com.example.library.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Error {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public Error(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Error(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = List.of(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
