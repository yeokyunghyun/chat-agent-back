package com.example.chat_agent_back.chat.route.scheduler;

import com.example.chat_agent_back.chat.route.service.ChatRouterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRouterScheduler {
    private final ChatRouterService chatRouterService;

    @Scheduled(fixedRate = 2000)
    public void routeOnce() {
        MDC.put("suppressSql", "true");
        chatRouterService.routeOnce();
        MDC.remove("suppressSql");
    }
}
