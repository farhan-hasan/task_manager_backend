package com.example.task_manager_backend.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private Boolean taskStatus;
    private LocalDateTime createdAt;
}
