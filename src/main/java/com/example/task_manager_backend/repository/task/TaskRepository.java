package com.example.task_manager_backend.repository.task;

import com.example.task_manager_backend.model.task.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Task> getAllTasks() {
        String query = "SELECT * FROM task";

        RowMapper<Task> taskRowMapper = ((resultSet, rowNum) ->
                new Task(
                    resultSet.getLong("task_id"),
                    resultSet.getString("task_title"),
                    resultSet.getString("task_description"),
                    resultSet.getBoolean("task_status"),
                    resultSet.getDate("created_at").toLocalDate().atStartOfDay()));

        return jdbcTemplate.query(query, taskRowMapper);
    }

    public Task getTaskById(Long taskId) {
        String query = "SELECT * FROM task WHERE task_id = ?";

        RowMapper<Task> taskRowMapper = (resultSet, rowNum) ->
                new Task(
                        resultSet.getLong("task_id"),
                        resultSet.getString("task_title"),
                        resultSet.getString("task_description"),
                        resultSet.getBoolean("task_status"),
                        resultSet.getDate("created_at").toLocalDate().atStartOfDay()
                );

        try {
            return jdbcTemplate.queryForObject(query, taskRowMapper, taskId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int createTask(Task task) {
        String query = "INSERT INTO task (task_title, task_description) " +
                "VALUES (?, ?)";

        return jdbcTemplate.update(query,
                task.getTaskTitle(),
                task.getTaskDescription());
    }

    public int updateTask(Task task, Long taskId) {
        String query = "UPDATE task SET task_title = ?, task_description = ?, task_status = ? " +
                "WHERE task_id = ?";

        return jdbcTemplate.update(query,
                task.getTaskTitle(),
                task.getTaskDescription(),
                task.getTaskStatus(), taskId);
    }

    public int deleteTask(Long taskId) {
        String query = "DELETE FROM task WHERE task_id = ?";
        return jdbcTemplate.update(query, taskId);
    }

}
