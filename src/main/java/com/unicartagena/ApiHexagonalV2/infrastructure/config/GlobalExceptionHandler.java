package com.unicartagena.ApiHexagonalV2.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage().toLowerCase();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (message.contains("no encontrad") || message.contains("no existe")) {
            status = HttpStatus.NOT_FOUND;
        } else if (message.contains("credenciales") || message.contains("email")) {
            status = HttpStatus.UNAUTHORIZED;
        }

        return ResponseEntity.status(status).body(Map.of("error", ex.getMessage()));
    }
}