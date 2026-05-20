package com.aquagrid.service;

import com.aquagrid.dto.LoginRequest;
import com.aquagrid.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}