package com.example.chat_agent_back.chat.main.service;

public interface ChatMainService {
    String getAgentStatus(String userId);
    void updateAgentStatus(String userId, String status);
}
