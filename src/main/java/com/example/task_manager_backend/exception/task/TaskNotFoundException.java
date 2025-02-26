package com.example.task_manager_backend.exception.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message){
        super(message);
    }
}
