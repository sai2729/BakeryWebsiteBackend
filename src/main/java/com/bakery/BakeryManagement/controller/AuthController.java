package com.bakery.BakeryManagement.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bakery.BakeryManagement.model.User;
import com.bakery.BakeryManagement.request.SignInRequest;
import com.bakery.BakeryManagement.response.SignInResponse;
import com.bakery.BakeryManagement.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
        try {
            user.setRole(User.Role.CUSTOMER); // Set default role
            userService.registerUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            return ResponseEntity.ok(response); // Send a JSON response
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        try {
            // Authenticate user
            SignInResponse response = userService.authenticateUser(signInRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PutMapping("/update-role/{userId}")
    public ResponseEntity<Map<String, String>> updateUserRole(@PathVariable int userId, @RequestBody Map<String, String> requestBody) {
        try {
            String newRole = requestBody.get("role");
            userService.updateUserRole(userId, newRole);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User role updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}