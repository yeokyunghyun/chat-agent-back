package com.example.chat_agent_back.domain.user.controller;

import com.example.chat_agent_back.common.security.jwt.JwtTokenProvider;
import com.example.chat_agent_back.domain.user.dto.request.LoginRequest;
import com.example.chat_agent_back.domain.user.dto.request.RegisterRequest;
import com.example.chat_agent_back.domain.user.entity.User;
import com.example.chat_agent_back.domain.user.repository.UserRepository;
import com.example.chat_agent_back.domain.user.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}

