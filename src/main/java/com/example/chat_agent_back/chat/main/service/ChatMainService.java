package com.example.chat_agent_back.chat.main.service;

import com.example.chat_agent_back.chat.main.dto.ChatRequest;

public interface ChatMainService {
    void insertChatRequest(ChatRequest req);
    String getAgentStatus(String userId);
    void updateAgentStatus(String userId, String status);
}
