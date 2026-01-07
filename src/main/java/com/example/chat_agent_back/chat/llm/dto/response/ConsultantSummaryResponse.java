package com.example.chat_agent_back.chat.llm.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConsultantSummaryResponse {
    private String message;
    private String consultType;
}
