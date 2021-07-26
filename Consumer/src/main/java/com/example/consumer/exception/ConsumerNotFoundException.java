package com.example.consumer.exception;

public class ConsumerNotFoundException extends RuntimeException{
    public ConsumerNotFoundException(Long id) {
            super(String.format("Customer with Id %d not found", id));
    }
}
