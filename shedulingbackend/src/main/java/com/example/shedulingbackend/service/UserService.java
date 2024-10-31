package com.example.shedulingbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shedulingbackend.model.User;
import com.example.shedulingbackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String email, String password, String role) throws Exception {
        if (!"user".equals(role) && !"admin".equals(role)) {
            throw new Exception("Invalid role");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Ensure to store the password securely in production
        user.setRole(role);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) { // Change to equals to compare passwords
            throw new Exception("Invalid credentials");
        }
        return user;
    }
}
