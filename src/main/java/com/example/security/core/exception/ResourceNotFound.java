package com.example.security.core.exception;

public class ResourceNotFound extends Exception{

    public ResourceNotFound(String message){
        super(message);
    }

    public ResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
