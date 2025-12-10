package com.example.chat_agent_back.chat.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String id;
    private String customerId;
    private String customerName;
    private String requestTime;
    private String status;
}