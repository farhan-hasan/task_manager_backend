package com.example.task_manager_backend.controller.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.task_manager_backend.exception.auth.RefreshTokenNotFoundException;
import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.model.user.UserModel;
import com.example.task_manager_backend.service.auth.JWTService;
import com.example.task_manager_backend.service.user.UserService;
import com.example.task_manager_backend.service.user_role.UserRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @GetMapping(value = "/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("IN AUTH CONTROLLER REFRESH TOKEN");
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = JWTService.getDecodedUsername(refreshToken);
                String username = decodedJWT.getSubject();
                UserModel user = userService.getUserByUsername(username);
                List<RoleModel> roles = userRoleService.getRolesByUserId(user.getId());
                String accessToken = JWTService.refreshAccessToken(user, roles);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            System.out.println("IN AUTH CONTROLLER REFRESH TOKEN NOT FOUND");
            throw new RefreshTokenNotFoundException("Refresh token not found");
        }

    }
}
