package com.ascendion.datapowerAI.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String password;
    private String roleName; // e.g., "Admin", "User"
}

