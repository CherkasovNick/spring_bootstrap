package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.dao.RoleRepository;
import com.example.spring_bootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    public Role getById(Long id) {
//        return getById(id);
        return roleRepository.findById(id).get();
    }

    public Role getRoleByName(String rolename) {
        return null;
    }

//    public Set<Role> getRoleSet() {
//        return roleRepository.getRoleSet();
//    }
//
//    public Set<Role> getRoleSetForUser(String[] rolenames) {
//        return roleRepository.getRoleSetForUser(rolenames);
//    }
}
