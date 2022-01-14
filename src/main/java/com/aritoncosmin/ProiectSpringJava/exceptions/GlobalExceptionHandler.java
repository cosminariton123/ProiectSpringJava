package com.aritoncosmin.ProiectSpringJava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "404 Not Found");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseParameters);
    }

    @ExceptionHandler({InternalServerError.class})
    public ResponseEntity<Map<String, String>> handleInternalServerError(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "500 Internal Server Error");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseParameters);
    }
}
