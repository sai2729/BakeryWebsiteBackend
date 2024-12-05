package com.bakery.BakeryManagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bakery.BakeryManagement.model.User;
import com.bakery.BakeryManagement.repository.UserRepository;
import com.bakery.BakeryManagement.request.SignInRequest;
import com.bakery.BakeryManagement.response.SignInResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        userRepository.save(user);
    }
    
    public SignInResponse authenticateUser(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail());

        if (user == null || !passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Return user details as a response
        return new SignInResponse(user.getUserId(), user.getName(), user.getEmail(), user.getRole().name());
    }
    
    public User findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }
}