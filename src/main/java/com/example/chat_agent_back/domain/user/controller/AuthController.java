package com.example.chat_agent_back.domain.user.controller;

import com.example.chat_agent_back.domain.user.dto.request.LoginRequest;
import com.example.chat_agent_back.domain.user.dto.request.LogoutRequest;
import com.example.chat_agent_back.domain.user.dto.request.RefreshTokenRequest;
import com.example.chat_agent_back.domain.user.dto.request.RegisterRequest;
import com.example.chat_agent_back.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // 이미 존재하는 사용자 체크
        return authService.register(request);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        return authService.logout(request);
    }

    @PostMapping("/api/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refresh(request);
    }
}

