package com.example.task_manager_backend.controller.user_role;

import com.example.task_manager_backend.request.user_role.AddRoleToUserRequest;
import com.example.task_manager_backend.response.ApiResponse;
import com.example.task_manager_backend.service.user_role.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user-role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addRoleToUser(@RequestBody AddRoleToUserRequest addRoleToUserRequest) {
        try {
            userRoleService.addRoleToUser(addRoleToUserRequest);
            return ResponseEntity.ok(new ApiResponse("Role added to user", addRoleToUserRequest.toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add role", e.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getRolesByUserId(@PathVariable Long id) {
        try {
            userRoleService.getRolesByUserId(id);
            return ResponseEntity.ok(new ApiResponse("Roles fetched successfully", userRoleService.getRolesByUserId(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch roles", e.getMessage()));
        }
    }

}
