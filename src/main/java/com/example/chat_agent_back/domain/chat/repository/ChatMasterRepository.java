package com.example.chat_agent_back.domain.chat.repository;

import com.example.chat_agent_back.domain.chat.entity.ChatMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMasterRepository extends JpaRepository<ChatMaster, Long> {

}
