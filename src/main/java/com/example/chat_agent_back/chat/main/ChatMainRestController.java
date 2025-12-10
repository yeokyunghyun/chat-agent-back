package com.example.chat_agent_back.chat.main;

import com.example.chat_agent_back.chat.main.dto.ChatMessage;
import com.example.chat_agent_back.chat.main.dto.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class ChatMainRestController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RestTemplate restTemplate;

    // 신규 상담 요청 전달 (고객 register 시 호출됨)
    @PostMapping("/api/requests")
    public void sendNewRequest(@RequestBody ChatRequest req) {
        messagingTemplate.convertAndSend("/topic/agent/requests", req);
        System.out.println("신규 상담 요청 = " + req.toString());
    }

    // 고객 => 상담사 메시지
    @PostMapping("/api/messages")
    public void sendMessage(@RequestBody ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/agent/" + message.getCustomerId(), message);
        System.out.println("고객 => 상담사 메시지 = " + message.toString());
    }

    // 상담사 => 고객 메시지
    @PostMapping("/api/agent/send")
    public void sendToCustomer(@RequestBody ChatMessage message) {
        // Node 주소/포트에 맞춰서 수정
        String nodeUrl = "http://localhost:3000/api/send-to-customer";
        restTemplate.postForEntity(nodeUrl, message, Void.class);
        System.out.println("상담사 => 고객 메시지 = " + message.toString());
    }
}
