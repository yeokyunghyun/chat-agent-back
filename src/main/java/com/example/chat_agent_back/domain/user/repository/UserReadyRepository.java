package com.example.chat_agent_back.domain.user.repository;

import com.example.chat_agent_back.domain.user.entity.UserReady;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadyRepository extends JpaRepository<UserReady, String> {
}
