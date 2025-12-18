package com.example.chat_agent_back.domain.inquiry.controller;

import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeDeleteRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeInsertRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeNameUpdateRequest;
import com.example.chat_agent_back.domain.inquiry.dto.response.InquiryTypeTreeResponse;
import com.example.chat_agent_back.domain.inquiry.service.InquiryMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InquiryMainRestController {

    private final InquiryMainService inquiryMainService;

    @GetMapping("/api/select/inquiryTypeTree")
    public List<InquiryTypeTreeResponse> getInquiryTypeTree() {
        return inquiryMainService.getInquiryTypeTree();
    }

    @PostMapping("/api/insert/inquiryType")
    public ResponseEntity<Void> insertInquiryType(@RequestBody InquiryTypeInsertRequest request) {
        inquiryMainService.insertInquiryType(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/update/inquiryTypeName")
    public ResponseEntity<Void> updateInquiryTypeName(@RequestBody InquiryTypeNameUpdateRequest request) {
        inquiryMainService.updateInquiryTypeName(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/delete/inquiryType")
    public ResponseEntity<Void> deleteInquiryType(@RequestBody InquiryTypeDeleteRequest request) {
        inquiryMainService.deleteInquiryType(request);
        return ResponseEntity.ok().build();
    }
}
