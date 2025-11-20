package com.example.chat_agent_back.domain.user.service;

import com.example.chat_agent_back.domain.user.dto.request.LoginRequest;
import com.example.chat_agent_back.domain.user.dto.request.LogoutRequest;
import com.example.chat_agent_back.domain.user.dto.request.RefreshTokenRequest;
import com.example.chat_agent_back.domain.user.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public ResponseEntity<?> register(@RequestBody RegisterRequest request);
    public ResponseEntity<?> login(@RequestBody LoginRequest request);
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request);
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request);
}
