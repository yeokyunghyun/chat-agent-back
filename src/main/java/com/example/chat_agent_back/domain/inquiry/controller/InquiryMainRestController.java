package com.example.chat_agent_back.domain.inquiry.controller;

import com.example.chat_agent_back.domain.inquiry.dto.response.InquiryTypeTreeResponse;
import com.example.chat_agent_back.domain.inquiry.service.InquiryMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquiryMainRestController {

    private final InquiryMainService inquiryMainService;

    @PostMapping("/api/select/inquiryTypeTree")
    public List<InquiryTypeTreeResponse> getInquiryTypeTree() {
        return inquiryMainService.
    }
}
