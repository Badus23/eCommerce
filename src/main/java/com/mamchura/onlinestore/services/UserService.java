package com.mamchura.onlinestore.services;

import com.mamchura.onlinestore.models.User;
import com.mamchura.onlinestore.models.enums.Role;
import com.mamchura.onlinestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        user.getRoles().add(Role.ROLE_ADMIN);
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
            user.setActive(true);
            userRepository.save(user);
        });
    }
}
