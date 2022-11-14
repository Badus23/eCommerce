package com.mamchura.onlinestore.services;

import com.mamchura.onlinestore.models.User;
import com.mamchura.onlinestore.models.enums.Role;
import com.mamchura.onlinestore.repositories.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        return true;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getList() {
        return userRepository.findAll();
    }

    public void banUser(int id) {
        userRepository.findById(id).ifPresent(user -> {
            if (user.isActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
            userRepository.save(user);
        });
    }

    public void changeUserRole(User user, Map<String, String> form) {
//        for (String key : form.keySet()) {
//            if (user.getRoles().contains(Role.valueOf(key)))
//                user.getRoles().add(Role.valueOf(key));
//        }
//        TODO// make more easier

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return (User) userRepository.findByEmail(principal.getName()).orElse(null);
    }
}
