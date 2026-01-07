package com.example.chat_agent_back.chat.llm.service;

import com.example.chat_agent_back.chat.llm.dto.request.ConsultantSummaryRequest;
import com.example.chat_agent_back.chat.llm.dto.response.ConsultantSummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class LlmMainServiceImpl implements LlmMainService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ConsultantSummaryResponse summaryConsultant(ConsultantSummaryRequest request) {

        String prompt = buildPrompt(request.getMessage());

        Map<String, Object> body = Map.of(
                "model", "llama3",
                "prompt", prompt,
                "stream", false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> httpRequest =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        "http://localhost:11434/api/generate",
                        httpRequest,
                        Map.class
                );

        String result = (String) response.getBody().get("response");

        // JSON 파싱
        try {
            result = extractJson(result);
            return objectMapper.readValue(result, ConsultantSummaryResponse.class);
        } catch (Exception e) {
            log.info("ollama 파싱 에러 {}", e.getMessage());
            return ConsultantSummaryResponse.builder()
                    .message("내용을 입력해주세요.")
                    .consultType("기타")
                    .build();
        }
    }


    private String buildPrompt(String message) {
        return """
                다음은 고객 상담 내용이다.
                
                [상담 내용]
                %s
                
                요구사항:
                1. 상담 내용을 2~3문장으로 한국말로 요약하라
                2. 상담 유형은 아래 3개 중 하나만 선택하라
                   - 건강 식품 문의
                   - 헬스 문의
                   - 기타
                3. 반드시 JSON 형식으로만 응답하라
                
                응답 형식:
                
                {
                  "message": "...",
                  "consultType": "..."
                }
                
                4. 응답 형식 1개 이외에 아무 문장 앞 뒤 어떤 단어도 사용하지마라
                """.formatted(message);
    }

    private String extractJson(String text) {
        int start = text.indexOf("{");
        int end = text.lastIndexOf("}");

        if (start == -1 || end == -1 || start > end) {
            throw new IllegalArgumentException("JSON 형식 응답 아님");
        }

        return text.substring(start, end + 1);
    }
}
