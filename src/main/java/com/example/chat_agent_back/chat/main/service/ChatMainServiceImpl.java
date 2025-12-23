package com.example.chat_agent_back.chat.main.service;

import com.example.chat_agent_back.chat.main.dto.ChatRequest;
import com.example.chat_agent_back.domain.cust.entity.CustReady;
import com.example.chat_agent_back.domain.cust.mapper.CustReadyMapper;
import com.example.chat_agent_back.domain.cust.repository.CustReadyRepositry;
import com.example.chat_agent_back.domain.user.entity.UserReady;
import com.example.chat_agent_back.domain.user.repository.UserReadyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatMainServiceImpl implements ChatMainService{

    private final UserReadyRepository userReadyRepository;
    private final CustReadyRepositry custReadyRepositry;

    private static final Set<String> ALLOWED = Set.of("READY", "NOT_READY");

    @Override
    public void insertChatRequest(ChatRequest req) {
        CustReady custReady = CustReadyMapper.from(req);
        custReadyRepositry.save(custReady);
    }

    @Override
    @Transactional(readOnly = true)
    public String getAgentStatus(String userId) {
        return userReadyRepository.findById(userId)
                .map(UserReady::getUserStatCd)
                .filter(ALLOWED::contains)
                .orElse("NOT_READY");
    }

    @Override
    @Transactional
    public void updateAgentStatus(String userId, String status) {
        String normalized = normalize(status);

        UserReady entity = userReadyRepository.findById(userId)
                .map(existing -> {
                    existing.setUserStatCd(normalized);
                    return existing;
                })
                .orElseGet(() -> UserReady.builder()
                        .userId(userId)
                        .userStatCd(normalized)
                        .build());

        userReadyRepository.save(entity);
    }

    private String normalize(String status) {
        if (status == null) {
            throw new IllegalArgumentException("status is null");
        }
        String s = status.trim().toUpperCase();

        if (!ALLOWED.contains(s)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        return s;
    }

}
