package com.example.spring_bootstrap.dao;

import com.example.spring_bootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

//    public Set<Role> getRoleBy();
//    public Set<Role> getRoleByForUser(String[] rolenames);

//    Set<Role> getRoleSet();
//    Set<Role> getRoleSetForUser(String[] rolenames);

}