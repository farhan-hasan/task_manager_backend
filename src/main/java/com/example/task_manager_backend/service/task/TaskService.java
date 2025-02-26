package com.example.task_manager_backend.service.task;

import com.example.task_manager_backend.exception.task.TaskNotFoundException;
import com.example.task_manager_backend.model.task.Task;
import com.example.task_manager_backend.repository.task.TaskRepository;
import com.example.task_manager_backend.request.task.CreateTaskRequest;
import com.example.task_manager_backend.request.task.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return  taskRepository.getAllTasks();
    }

    public Task getTaskById(Long taskId) {
        Task task = taskRepository.getTaskById(taskId);
        if(task == null) {
            throw new TaskNotFoundException("Task does not exist.");
        }
        return task;
    }

    public int createTask(CreateTaskRequest createTaskRequest) {
        Task task = new Task();
        task.setTaskTitle(createTaskRequest.getTaskTitle());
        task.setTaskDescription(createTaskRequest.getTaskDescription());
        return taskRepository.createTask(task);
    }

    public int deleteTask(Long taskId) {
        Task task = getTaskById(taskId);
        if(task == null) {
            throw new TaskNotFoundException("Task does not exist");
        }
        return taskRepository.deleteTask(taskId);
    }

    public int updateTask(UpdateTaskRequest updateTaskRequest, Long taskId) {
        Task task = getTaskById(taskId);
        if(updateTaskRequest.getTaskTitle() != null && !updateTaskRequest.getTaskTitle().isEmpty()) {
            task.setTaskTitle(updateTaskRequest.getTaskTitle());
        }
        if(updateTaskRequest.getTaskDescription() != null) {
            task.setTaskDescription(updateTaskRequest.getTaskDescription());
        }
        if(updateTaskRequest.getTaskStatus() != null) {
            task.setTaskStatus(updateTaskRequest.getTaskStatus());
        }
        return taskRepository.updateTask(task, taskId);
    }
}
