package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.dao.RoleDAO;
import com.example.spring_bootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void delete(Role role) {
    }

    @Override
    public Set<Role> getRoleSet() {
        return roleDAO.getRoleSet();
    }

}
