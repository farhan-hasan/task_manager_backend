package com.example.task_manager_backend.repository.user_role;

import com.example.task_manager_backend.model.role.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public void addRoleToUser(Long userId, Long roleId) {
        String sql = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, userId, roleId);
    }

    public List<RoleModel> getRolesByUserId(Long userId) {
        String sql = "SELECT r.id, r.name FROM roles r " +
                "JOIN user_roles ur ON r.id = ur.role_id " +
                "WHERE ur.user_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RoleModel role = new RoleModel();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            return role;
        }, userId);
    }

}
