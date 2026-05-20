package com.aquagrid.service.impl;

import com.aquagrid.dto.AuthResponse;
import com.aquagrid.dto.LoginRequest;
import com.aquagrid.dto.RegisterRequest;
import com.aquagrid.exception.BadRequestException;
import com.aquagrid.model.User;
import com.aquagrid.model.UserRole;
import com.aquagrid.repository.UserRepository;
import com.aquagrid.security.jwt.JwtService;
import com.aquagrid.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        if (repository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();

        repository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid username or password"
                        )
                );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new BadCredentialsException(
                    "Invalid username or password"
            );
        }

        String token = jwtService.generateToken(
                user.getUsername()
        );

        return new AuthResponse(token);
    }
}