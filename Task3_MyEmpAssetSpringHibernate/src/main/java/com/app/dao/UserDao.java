package com.app.dao;

import com.app.model.User;
import java.util.List;

public interface UserDao {

    void insert(User user);
    void delete(int id);
    void update(User user);
    List<User> getAllUsers();
    User getById(int id);
}