package com.example.chat_agent_back.chat.main.controller;

import com.example.chat_agent_back.chat.main.dto.ChatMessage;
import com.example.chat_agent_back.chat.main.dto.ChatRequest;
import com.example.chat_agent_back.chat.main.dto.ChatStatusUpdateRequest;
import com.example.chat_agent_back.chat.main.service.ChatMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ChatMainRestController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RestTemplate restTemplate;
    private final ChatMainService chatMainService;

    // 신규 상담 요청 전달 (고객 register 시 호출됨)
    @PostMapping("/api/requests")
    public void sendNewRequest(@RequestBody ChatRequest req) {
//        messagingTemplate.convertAndSend("/topic/agent/requests", req);
        chatMainService.insertChatRequest(req);
        System.out.println("신규 상담 요청 = " + req.toString());
    }

    // 고객 => 상담사 메시지
    @PostMapping("/api/messages")
    public void sendMessage(@RequestBody ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/agent/" + message.getCustomerId(), message);
        System.out.println("고객 => 상담사 메시지 = " + message.toString());
    }

    // 상담사 => 고객 메시지
    @MessageMapping("/agent/send")
    public void sendToCustomer(ChatMessage message) {
        String nodeUrl = "http://localhost:3000/api/send-to-customer";
        restTemplate.postForEntity(nodeUrl, message, Void.class);
        System.out.println("상담사 => 고객 메시지 = " + message.toString());
    }

    // 상담사 상태 조회 (로그인한 사용자 기준)
    @PostMapping("/api/stat/select")
    public ResponseEntity<String> selectStat(Authentication authentication) {
        String userId =  authentication.getName();
        String status = chatMainService.getAgentStatus(userId);
        return ResponseEntity.ok(status);
    }

    // 상담사 상태 변경
    @PostMapping(value = "/api/stat/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatJson(Authentication authentication,
                                            @RequestBody ChatStatusUpdateRequest req) {
        String userId = authentication.getName();
        String status = req.getStatus();

        chatMainService.updateAgentStatus(userId, status);

//        messagingTemplate.convertAndSend("/topic/agent/status", Map.of(
//                "userId", userId,
//                "status", status
//        ));

        return ResponseEntity.ok().build();
    }

    // 상담사가 상담건 클릭 시
    @PostMapping("/api/agent/start")
    public void startConsultation(@RequestBody ChatRequest req) {
        // Node 서버에 알림
        restTemplate.postForEntity(
                "http://localhost:3000/api/consultation-started",
                Map.of("customerId", req.getCustomerId()),
                Void.class
        );
    }

}
