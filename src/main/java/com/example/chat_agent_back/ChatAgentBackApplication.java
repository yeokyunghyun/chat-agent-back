package com.example.chat_agent_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatAgentBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAgentBackApplication.class, args);
	}

}
