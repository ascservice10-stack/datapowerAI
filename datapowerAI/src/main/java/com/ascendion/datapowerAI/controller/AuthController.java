package com.ascendion.datapowerAI.controller;

import com.ascendion.datapowerAI.dto.AuthResponse;
import com.ascendion.datapowerAI.dto.LoginRequest;
import com.ascendion.datapowerAI.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent())
            return ResponseEntity.badRequest().body("Username already exists");
        var user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .email(req.email())
                .build();
        User finalUser = user;
        //roleRepo.findByName("ROLE_USER").ifPresent(r -> finalUser.getRoles().add(r));
        userRepo.save(user);
        return ResponseEntity.ok("User created");
    }

    @GetMapping("/sso/success")
    public ResponseEntity<?> ssoSuccess(@AuthenticationPrincipal OidcUser oidcUser) {
        String username = oidcUser.getPreferredUsername() != null ? oidcUser.getPreferredUsername() : oidcUser.getEmail();
        var user = userRepo.findByUsername(username).orElseGet(() -> {
            User u = User.builder().username(username).email(oidcUser.getEmail()).password("").build();
            //roleRepo.findByName("ROLE_USER").ifPresent(r -> u.getRoles().add(r));
            return userRepo.save(u);
        });
        //String rolesCsv = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(","));
        String token = jwtTokenProvider.createToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer", 3600));
    }*/
}
