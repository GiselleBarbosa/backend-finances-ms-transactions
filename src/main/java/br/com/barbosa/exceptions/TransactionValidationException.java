package br.com.barbosa.exceptions;

public class TransactionValidationException extends RuntimeException {
    public TransactionValidationException(String message) {
        super(message);
    }
}