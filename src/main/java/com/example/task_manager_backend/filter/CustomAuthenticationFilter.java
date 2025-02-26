package com.example.task_manager_backend.filter;

import com.example.task_manager_backend.model.user.UserModel;
import com.example.task_manager_backend.response.ResponseWriter;
import com.example.task_manager_backend.service.auth.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("IN ATTEMPT AUTHENTICATION");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("USERNAME:" + username);
        System.out.println("PASSWORD:" + password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            System.out.println("AUTHENTICATION FAILED: " + e.getMessage());
            try {
                ResponseWriter.writeResponse(response,"error",e);
            } catch (IOException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("IN SUCCESSFUL AUTHENTICATION");
        User user = (User) authResult.getPrincipal();
        String accessToken = JWTService.generateAccessToken(user);
        String refreshToken = JWTService.generateRefreshToken(user);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",accessToken);
        tokens.put("refresh_token",refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
