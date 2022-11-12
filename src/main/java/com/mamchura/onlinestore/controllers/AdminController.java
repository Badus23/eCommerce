package com.mamchura.onlinestore.controllers;

import com.mamchura.onlinestore.services.ProductService;
import com.mamchura.onlinestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("users", userService.getList());
        return "admin";
    }

    @PostMapping("/user/ban/{id}")
    public String banUser(@PathVariable("id") int id) {
        userService.banUser(id);
        return "redirect:/admin";
    }
}
