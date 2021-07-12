package com.example.spring_bootstrap.controller;

import com.example.spring_bootstrap.model.User;
import com.example.spring_bootstrap.service.RoleServiceImpl;
import com.example.spring_bootstrap.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;


    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("allUsersList", userService.allUsers());
        return "admin";
    }

    @GetMapping(value = "/new")
    public ModelAndView newUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");
        modelAndView.addObject("user", new User());
//        modelAndView.addObject("rolesList", roleService.getRoleSet());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
//        model.addAttribute("rolesList", roleService.getRoleSet());
        return "edit";
    }

    @PostMapping(value = "")
    public String newUserPost(@ModelAttribute("user") User user,
                              @RequestParam("selectedRoles") String[] selectedRoles) {
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @PatchMapping(value = "/{id}")
    public String editUserPatch(@ModelAttribute("user") User user, HttpServletRequest req) {
        String[] selectedRoles = req.getParameterValues("selectedRoles");
        userService.update(user, selectedRoles);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") long id) {
//        userService.delete(userService.getById(id));
        userService.delete(id);
        return "redirect:/admin";
    }


}