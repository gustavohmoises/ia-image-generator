package com.ia_image_generator.image_validate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(
            IllegalArgumentException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Dados inválidos.");

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                message
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneric(
            Exception ex) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno."
        );
    }

    private Map<String, Object> buildResponse(
            HttpStatus status,
            String message) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", status.value());
        response.put("timestamp", Instant.now());
        response.put("message", message);

        return response;
    }
}