package com.mamchura.onlinestore.controllers;

import com.mamchura.onlinestore.models.User;
import com.mamchura.onlinestore.security.UserDetails;
import com.mamchura.onlinestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "This email is already registered!");
            return "registration";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String helloPage() {
        return "hello";
    }
}
