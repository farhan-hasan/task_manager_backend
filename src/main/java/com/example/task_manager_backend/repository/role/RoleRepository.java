package com.example.task_manager_backend.repository.role;

import com.example.task_manager_backend.model.role.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleModel saveRole(RoleModel role) {
        String sql = "INSERT INTO roles (name) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, role.getName());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            role.setId(keyHolder.getKey().longValue());
        }

        return role;
    }

}
