package com.example.chat_agent_back.chat.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String customerId;
    private String customerName;
    LocalDateTime requestTime;
    private String status;
}