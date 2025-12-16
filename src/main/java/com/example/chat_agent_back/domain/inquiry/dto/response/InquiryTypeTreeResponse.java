package com.example.chat_agent_back.domain.inquiry.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryTypeTreeResponse {

    private Long id;
    private String label;
    private List<InquiryTypeTreeResponse> children;

}
