package com.ascendion.datapowerAI.service;

import com.ascendion.datapowerAI.dto.AuthResponse;
import com.ascendion.datapowerAI.dto.LoginRequest;
import com.ascendion.datapowerAI.entity.User;
import com.ascendion.datapowerAI.exception.InvalidCredentialsException;
import com.ascendion.datapowerAI.repository.RoleRepository;
import com.ascendion.datapowerAI.repository.UserRepository;
import com.ascendion.datapowerAI.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepo,
                       RoleRepository roleRepo,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthResponse login(LoginRequest request) {
        var userOpt = userRepo.findByUsername(request.username());
        if (userOpt.isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(user.getUsername());
        return new AuthResponse(token, "Bearer", user.getRole().getName());
    }
}
