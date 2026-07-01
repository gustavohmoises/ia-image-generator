package com.ia_image_generator.image_request.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleJsonParse(
            HttpMessageNotReadableException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "JSON inválido ou tipo de dado incorreto"
        );
    }

    private Map<String, Object> buildResponse(
            HttpStatus status,
            String message) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", status.value());
        response.put("timestamp", Instant.now().toString());
        response.put("message", message);

        return response;
    }
}