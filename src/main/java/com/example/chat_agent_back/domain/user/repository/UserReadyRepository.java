package com.example.chat_agent_back.domain.user.repository;

import com.example.chat_agent_back.domain.user.entity.UserReady;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReadyRepository extends JpaRepository<UserReady, String> {
    Optional<UserReady> findFirstByUserStatCdOrderByAssignCustCntAsc(String userStatCd);
}
