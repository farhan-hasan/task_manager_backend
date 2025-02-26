package com.example.task_manager_backend.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {
    private String name;
    private String email;
    private String password;
}
