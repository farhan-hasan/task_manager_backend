package com.example.task_manager_backend.request.user_role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleToUserRequest {
    private Long userId;
    private Long roleId;
}
