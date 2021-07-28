package com.example.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptions {

    @ExceptionHandler(ConsumerNotFoundException.class)
    protected ResponseEntity<Object> handleConsumerNotFound
            (ConsumerNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Consumer not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
