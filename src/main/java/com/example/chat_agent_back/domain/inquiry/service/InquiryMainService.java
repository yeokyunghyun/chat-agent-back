package com.example.chat_agent_back.domain.inquiry.service;

import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeDeleteRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeInsertRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeUpdateRequest;
import com.example.chat_agent_back.domain.inquiry.dto.response.InquiryTypeTreeResponse;

import java.util.List;

public interface InquiryMainService {
    public List<InquiryTypeTreeResponse> getInquiryTypeTree();
    public void insertInquiryType(InquiryTypeInsertRequest request);
    public void updateInquiryType(InquiryTypeUpdateRequest request);
    public void deleteInquiryType(InquiryTypeDeleteRequest request);
}
