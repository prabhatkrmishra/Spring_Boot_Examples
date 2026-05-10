package com.project.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HotelNotFoundException.class)
    // "HotelNotFoundException ex" contains thrown exception object.
    public ResponseEntity<String> handleHotelNotFoundException(HotelNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    /*
       If validation fails and Spring throws MethodArgumentNotValidException,
       call the method below.

       Client sends invalid JSON
                    ↓
       @Valid detects violations
                    ↓
       Spring throws MethodArgumentNotValidException
                    ↓
       @RestControllerAdvice catches it
                    ↓
       This handler method executes
                    ↓
       Validation errors converted to JSON map
                    ↓
       400 Bad Request returned
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}