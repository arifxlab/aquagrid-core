package com.aquagrid.service;

import com.aquagrid.dto.AuthResponse;
import com.aquagrid.dto.LoginRequest;
import com.aquagrid.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}