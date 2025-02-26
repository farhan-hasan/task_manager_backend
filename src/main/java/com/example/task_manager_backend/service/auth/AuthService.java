package com.example.task_manager_backend.service.auth;

import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.model.user.UserModel;
import com.example.task_manager_backend.repository.user.UserRepository;
import com.example.task_manager_backend.repository.user_role.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.getUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<RoleModel> roles;
            roles = userRoleRepository.getRolesByUserId(user.getId());
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }
    }
}
