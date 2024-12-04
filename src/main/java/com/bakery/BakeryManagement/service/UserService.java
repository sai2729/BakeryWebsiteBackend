package com.bakery.BakeryManagement.service;

import com.bakery.BakeryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakery.BakeryManagement.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder instead of BCryptPasswordEncoder

    public String signup(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "User already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Use PasswordEncoder
        userRepository.save(user);
        return "Signup successful!";
    }

    public String login(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // Use PasswordEncoder
                return "Login successful!";
            }
            return "Invalid password!";
        }
        return "User not found. Please sign up.";
    }
}
