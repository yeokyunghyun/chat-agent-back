package com.example.chat_agent_back.domain.user.service;

import com.example.chat_agent_back.common.security.jwt.JwtTokenProvider;
import com.example.chat_agent_back.domain.user.dto.request.LoginRequest;
import com.example.chat_agent_back.domain.user.dto.request.RegisterRequest;
import com.example.chat_agent_back.domain.user.entity.User;
import com.example.chat_agent_back.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "이미 존재하는 아이디입니다."));
        }

        // 비밀번호 해싱
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 저장
        User user = User.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "회원가입 성공"));
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication authentication = authManagerBuilder.getObject().authenticate(authToken);
        // UserDetailService에서 loadUserByUsername
        // passwordEncoder.matches(rawPassword, encodedPassword);

        String token = jwtTokenProvider.createToken(authentication.getName());

        return ResponseEntity.ok(Map.of("accessToken", token));
    }
}
