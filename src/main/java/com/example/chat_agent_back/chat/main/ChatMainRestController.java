package com.example.chat_agent_back.chat.main;

import com.example.chat_agent_back.chat.main.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMainRestController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/api/messages")
    public void sendMessage(@RequestBody ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/agent", message);
        System.out.println("message = " + message.toString());
    }
}
