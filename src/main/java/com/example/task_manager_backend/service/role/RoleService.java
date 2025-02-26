package com.example.task_manager_backend.service.role;

import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.repository.role.RoleRepository;
import com.example.task_manager_backend.request.role.SaveRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleModel saveRole(SaveRoleRequest saveRoleRequest) {
        RoleModel role = new RoleModel();
        role.setName(saveRoleRequest.getName());
        return roleRepository.saveRole(role);
    }

}
