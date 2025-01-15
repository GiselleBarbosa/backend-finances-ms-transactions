package br.com.barbosa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTransactionNotFoundException(TransactionNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("error", "TransactionNotFound");
        response.put("status", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TransactionValidationException.class)
    public ResponseEntity<Map<String, String>> handleTransactionValidationException(TransactionValidationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("error", "ValidationError");
        response.put("status", "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
