package com.example.spring_bootstrap.service;

import com.example.spring_bootstrap.dao.RoleRepository;
import com.example.spring_bootstrap.dao.UserRepository;
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


@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
//        return userRepository.getById(id);
        return userRepository.findById(id).get();
    }

    public void save(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(roleRepository.getRoleSetForUser(roles));
        userRepository.save(user);
    }

    public void update(User user, String[] roles) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
//        user.setRoles(roleRepository.getRoleSetForUser(roles));
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    //UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByName(username);
    }

    //    PasswordEncoderBean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
