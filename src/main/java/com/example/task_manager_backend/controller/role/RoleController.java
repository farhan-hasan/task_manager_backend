package com.example.task_manager_backend.controller.role;


import com.example.task_manager_backend.request.role.SaveRoleRequest;
import com.example.task_manager_backend.response.ApiResponse;
import com.example.task_manager_backend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveRole(@RequestBody SaveRoleRequest saveRoleRequest) {
        try {
            return ResponseEntity.ok(new ApiResponse("Role saved successfully", roleService.saveRole(saveRoleRequest)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to save role", e.getMessage()));
        }
    }

}
