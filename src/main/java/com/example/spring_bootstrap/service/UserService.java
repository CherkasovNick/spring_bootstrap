package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    User getById(Long id);
    void save(User user, String[] roles);
    void update(User user, String[] roles);
    void delete(User user);
    String showRoles(User user);
}

