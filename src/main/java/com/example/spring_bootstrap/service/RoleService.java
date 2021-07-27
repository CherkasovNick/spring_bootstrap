package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.model.Role;

import java.util.Set;

public interface RoleService {
    void delete(Role role);
    Set<Role> getRoleSet();
}

