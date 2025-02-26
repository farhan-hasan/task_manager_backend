package com.example.task_manager_backend.service.user_role;

import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.repository.user_role.UserRoleRepository;
import com.example.task_manager_backend.request.user_role.AddRoleToUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public void addRoleToUser(AddRoleToUserRequest addRoleToUserRequest) {
        userRoleRepository.addRoleToUser(addRoleToUserRequest.getUserId(), addRoleToUserRequest.getRoleId());
    }

    public List<RoleModel> getRolesByUserId(Long userId) {
        return userRoleRepository.getRolesByUserId(userId);
    }

}
