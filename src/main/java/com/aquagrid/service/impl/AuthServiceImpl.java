package com.aquagrid.service.impl;

import com.aquagrid.dto.LoginRequest;
import com.aquagrid.dto.LoginResponse;
import com.aquagrid.service.AuthService;
import com.aquagrid.security.jwt.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    public AuthServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // TEMP LOGIN (we will replace with DB later)
        if (!request.getUsername().equals("admin") ||
                !request.getPassword().equals("admin123")) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(request.getUsername());

        return new LoginResponse(token);
    }
}