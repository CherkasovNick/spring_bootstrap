package com.example.spring_bootstrap.dao;


import com.example.spring_bootstrap.model.Role;

import java.util.Set;

public interface RoleDAO {

    void delete(Role role);

    Role getRoleByName(String rolename);
    Set<Role> getRoleSet();
    Set<Role> getRoleSetForUser(String[] rolenames);
}