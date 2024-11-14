package br.edu.unicesumar.folia.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private String errorCode;
    private String message;
    private int status;

    public ApiError(String errorCode, String message, int status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
}
