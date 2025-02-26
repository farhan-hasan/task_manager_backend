package com.example.task_manager_backend.repository.user;

import com.example.task_manager_backend.model.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserModel saveUser(UserModel user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().longValue());
        }

        System.out.println(user.toString());

        return user;
    }

    public UserModel getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try{
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                UserModel user = new UserModel();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return user;
            }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserModel getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                UserModel user = new UserModel();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return user;
            }, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserModel getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                UserModel user = new UserModel();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return user;
            }, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<UserModel> getAllUsers() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserModel user = new UserModel();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return user;
        });
    }

}
