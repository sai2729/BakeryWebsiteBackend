package com.bakery.BakeryManagement.service;

import com.bakery.BakeryManagement.model.User;
import com.bakery.BakeryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email already in use!";
        }

        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .role(Role.CUSTOMER) // default role
                .build();

        userRepository.save(user);
        return "Registration successful!";
    }

    public String login(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword())) {
            return "Invalid email or password!";
        }

        return "Login successful!";
    }
}
