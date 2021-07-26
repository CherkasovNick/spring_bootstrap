package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.model.Role;

import java.util.Set;

public interface RoleService {
    void save(Role role);
    void delete(Role role);
    Role getById(Long id);
    Role getRoleByName(String rolename);
    Set<Role> getRoleSet();
    Set<Role> getRoleSetForUser(String[] rolenames);
}

