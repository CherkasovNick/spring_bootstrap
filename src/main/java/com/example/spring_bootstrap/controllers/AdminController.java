package com.example.spring_bootstrap.controllers;

import com.example.spring_bootstrap.model.User;
import com.example.spring_bootstrap.service.RoleService;
import com.example.spring_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", user);
        model.addAttribute("userRoles", userService.showRoles(user));
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getRoleSet());
        return "admin";
    }

    //добаваить new user
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "roleList") String[] selectedRoles) {
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

    //изменить user
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "roleList") String[] selectedRoles) {
        userService.update(user, selectedRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }
}