package com.example.chat_agent_back.domain.user.service;

public interface UserReadyService {
    public void upsertReadyOnLogin(String userId);
}
