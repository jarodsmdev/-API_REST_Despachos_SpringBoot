package com.citt.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;


@Data
public class ErrorMessage {
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage(HttpStatus status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
