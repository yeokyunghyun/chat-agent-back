package com.example.chat_agent_back.domain.cust.mapper;

import com.example.chat_agent_back.chat.main.dto.ChatRequest;
import com.example.chat_agent_back.domain.cust.entity.CustReady;

import java.time.LocalDateTime;

public final class CustReadyMapper {

    private CustReadyMapper() {}

    public static CustReady from(ChatRequest req) {
        return CustReady.builder()
                .userKey(req.getCustomerId())
                .custName(req.getCustomerName())
                .talkStatCd(req.getStatus())
                .regrDttm(LocalDateTime.now())
                .build();
    }

}
