package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.dto.UserRegistrationRequest;
import com.ascendion.datapowerAI.dto.UserResponse;
import com.ascendion.datapowerAI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Register new user
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request);
    }

    // Get all users (Admin use)
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get logged-in user details
    @GetMapping("/me")
    public UserResponse getLoggedInUser() {
        return userService.getLoggedInUser();
    }
}

