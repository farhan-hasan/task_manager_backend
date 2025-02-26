package com.example.task_manager_backend.controller.user;

import com.example.task_manager_backend.request.user.SaveUserRequest;
import com.example.task_manager_backend.response.ApiResponse;
import com.example.task_manager_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveUser(@RequestBody SaveUserRequest saveUserRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        try {
            return ResponseEntity.created(uri).body(new ApiResponse("User saved successfully",userService.saveUser(saveUserRequest)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to save user", e.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse("User fetched successfully", userService.getUserById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch user", e.getMessage()));
        }
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<Object> getUserByUserName(@PathVariable String username) {
        try {
            return ResponseEntity.ok(new ApiResponse("User fetched successfully", userService.getUserByUsername(username)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch user", e.getMessage()));
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllUsers () {
        try {
            return ResponseEntity.ok(new ApiResponse("Users fetched successfully", userService.getAllUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to fetch users", e.getMessage()));
        }
    }

}
