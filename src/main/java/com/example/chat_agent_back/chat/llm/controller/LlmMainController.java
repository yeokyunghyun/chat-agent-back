package com.example.chat_agent_back.chat.llm.controller;

import com.example.chat_agent_back.chat.llm.dto.request.ConsultantSummaryRequest;
import com.example.chat_agent_back.chat.llm.dto.response.ConsultantSummaryResponse;
import com.example.chat_agent_back.chat.llm.service.LlmMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LlmMainController {
    private final LlmMainService llmMainService;

    /* Ollama 다운로드
    * ollama run llama3
    * */

    @PostMapping("/api/chat/summary")
    public ResponseEntity<?> summaryConsultant(@RequestBody ConsultantSummaryRequest request) {
        ConsultantSummaryResponse response = llmMainService.summaryConsultant(request);

        return ResponseEntity.ok(response);
    }
}
