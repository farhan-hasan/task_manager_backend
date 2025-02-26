package com.example.task_manager_backend.exception.auth;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException(String message){
        super(message);
    }
}

