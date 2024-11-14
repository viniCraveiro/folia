package br.edu.unicesumar.folia.exception;

public class UserUuidNotFoundException extends RuntimeException {
    public UserUuidNotFoundException(String message) {
        super(message);
    }
}

