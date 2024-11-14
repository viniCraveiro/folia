package br.edu.unicesumar.folia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserUuidNotFoundException.class)
    public ResponseEntity<ApiError> handleUserUuidNotFound(UserUuidNotFoundException error) {
        ApiError apiError = new ApiError("UserUuidNotFoundException", error.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
