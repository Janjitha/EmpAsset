package com.dao;

import com.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    JdbcTemplate jdbcTemplate;
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        String sql = "insert into users(username,password,email,role) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
        System.out.println("User Inserted");
    }

    public void delete(int id) {
        String sql = "delete from users where id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("User Deleted");
    }

    public void update(User user) {
        String sql = "update users set username=?,password=?,email=?,role=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getId());
        System.out.println("User Updated");
    }

    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, (rs,rowNum)->mapRow(rs));
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        return user;
    }
}