package com.example.spring_bootstrap.dao;


import com.example.spring_bootstrap.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    User getById(Long id);
    void save(User user);
    void delete(User user);
    User getUserByEmail(String email);

}
