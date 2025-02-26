package com.example.task_manager_backend.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.task_manager_backend.model.role.RoleModel;
import com.example.task_manager_backend.model.user.UserModel;
import com.example.task_manager_backend.service.user_role.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {

    private static final String SECRET_KEY = "J9d/Kv3Nl2zU4wR9edmWnX/UMtU+OUk1lb7BVoqAB6k=";

    public static String generateAccessToken(User user) {
        Algorithm algorithm = getAlgorithm();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000))
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        return accessToken;
    }

    public static String refreshAccessToken(UserModel user, List<RoleModel> roles) {
        Algorithm algorithm = getAlgorithm();
        String accessToken = JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .withClaim("roles",roles.stream().map(RoleModel::getName).collect(Collectors.toList()))
                .sign(algorithm);
        return accessToken;
    }

    public static String generateRefreshToken(User user) {
        Algorithm algorithm = getAlgorithm();
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3500 * 60 * 1000))
                .sign(algorithm);
        return refreshToken;
    }

    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY.getBytes());
    }

    public static DecodedJWT getDecodedUsername(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

}
