package com.example.task_manager_backend.service.user;

import com.example.task_manager_backend.exception.user.UserNotFoundException;
import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.model.user.UserModel;
import com.example.task_manager_backend.repository.user.UserRepository;
import com.example.task_manager_backend.repository.user_role.UserRoleRepository;
import com.example.task_manager_backend.request.user.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModel saveUser(SaveUserRequest saveUserRequest) {
        UserModel user = new UserModel();
        user.setName(saveUserRequest.getName());
        user.setEmail(saveUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        return userRepository.saveUser(user);
    }

    public UserModel getUserById(Long id) {
        UserModel user = userRepository.getUserById(id);
        if(user == null) {
            throw new UserNotFoundException("User does not exist.");
        }
        return user;
    }

    public UserModel getUserByUsername(String username) {
        UserModel user = userRepository.getUserByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("User does not exist.");
        }
        return user;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
