package com.example.chat_agent_back.chat.llm.service;

import com.example.chat_agent_back.chat.llm.dto.request.ConsultantSummaryRequest;
import com.example.chat_agent_back.chat.llm.dto.response.ConsultantSummaryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface LlmMainService {
    public ConsultantSummaryResponse summaryConsultant(ConsultantSummaryRequest request);
}
