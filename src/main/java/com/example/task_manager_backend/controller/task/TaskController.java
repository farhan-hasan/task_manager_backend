package com.example.task_manager_backend.controller.task;

import com.example.task_manager_backend.request.task.CreateTaskRequest;
import com.example.task_manager_backend.request.task.UpdateTaskRequest;
import com.example.task_manager_backend.response.ApiResponse;
import com.example.task_manager_backend.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllTasks() {
        try {
            return ResponseEntity.ok(new ApiResponse("Tasks fetched successfully", taskService.getAllTasks()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch tasks", e.getMessage()));
        }
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long taskId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Task fetched successfully", taskService.getTaskById(taskId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch task", e.getMessage()));
        }
    }

    @PostMapping(value = "/create-task")
    public ResponseEntity<Object> createTask(@ModelAttribute CreateTaskRequest createTaskRequest) {
        try {
            return ResponseEntity.ok(new ApiResponse("Task created successfully", taskService.createTask(createTaskRequest)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to create task", e.getMessage()));
        }
    }

    @DeleteMapping(value = "/delete-task/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long taskId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Task deleted successfully", taskService.deleteTask(taskId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to delete task", e.getMessage()));
        }
    }

    @PostMapping(value = "/update-task/{taskId}")
    public ResponseEntity<Object> updateTask(@ModelAttribute UpdateTaskRequest updateTaskRequest, @PathVariable Long taskId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Task updated successfully", taskService.updateTask(updateTaskRequest, taskId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to update task", e.getMessage()));
        }
    }

}
