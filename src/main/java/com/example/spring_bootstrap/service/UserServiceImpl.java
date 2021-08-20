package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.dao.RoleDAO;
import com.example.spring_bootstrap.dao.UserDAO;
import com.example.spring_bootstrap.model.Role;
import com.example.spring_bootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public void save(User user, String[] roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleDAO.getRoleSetForUser(roles));
        userDAO.save(user);
    }

    @Override
    public void update(User user, String[] roles) {
        if (!user.getPassword().equals(userDAO.getById(user.getId()).getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setRoles(roleDAO.getRoleSetForUser(roles));
        userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    //      UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.getUserByEmail(email);
    }

    //      PasswordEncoderBean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public String showRoles(User user) {
        Set<Role> userRolesSet = user.getRoles();
        StringBuilder userRoles = new StringBuilder();
        for (Role role : userRolesSet) {
            userRoles.append(role.toString());
        }
        return userRoles.toString();
    }

}
