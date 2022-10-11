package com.springboot.springorderapp.exception;

public class MethodNotAllowedException extends RuntimeException{
    private String message;

    public MethodNotAllowedException(String message) {
        super(message);
        this.message = message;
    }
}
