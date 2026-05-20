package com.aquagrid.controller;

import com.aquagrid.dto.AuthResponse;
import com.aquagrid.dto.LoginRequest;
import com.aquagrid.dto.RegisterRequest;
import com.aquagrid.response.ApiResponse;
import com.aquagrid.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        service.register(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "User registered successfully",
                        null
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login successful",
                        service.login(request)
                )
        );
    }
}