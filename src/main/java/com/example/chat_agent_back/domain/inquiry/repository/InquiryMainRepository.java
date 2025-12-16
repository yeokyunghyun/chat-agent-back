package com.example.chat_agent_back.domain.inquiry.repository;

import com.example.chat_agent_back.domain.inquiry.entity.ChatInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryMainRepository extends JpaRepository<ChatInquiry, Long> {
    List<ChatInquiry> findAll();
}
