package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.dto.UserRegistrationRequest;
import com.ascendion.datapowerAI.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getLoggedInUser();
}

