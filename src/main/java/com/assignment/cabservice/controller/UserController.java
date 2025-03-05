package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.User;
import com.assignment.cabservice.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users") // URL: http://localhost:8080/users
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userlist"; // This will look for userlist.jsp
    }


    @GetMapping("/register")
    public String showRegistrationPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "register"; // Loads register.jsp
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/userlist"; }
}