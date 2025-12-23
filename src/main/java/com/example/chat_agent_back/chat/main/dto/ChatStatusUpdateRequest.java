package com.example.chat_agent_back.chat.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatStatusUpdateRequest {
    private String status; // "READY" | "NOT_READY"
}
