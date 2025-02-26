package com.example.task_manager_backend.request.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
    private String taskTitle;
    private String taskDescription;
    private Boolean taskStatus;
}
