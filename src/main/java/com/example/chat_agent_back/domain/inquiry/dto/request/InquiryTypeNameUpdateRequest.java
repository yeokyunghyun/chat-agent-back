package com.example.chat_agent_back.domain.inquiry.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryTypeNameUpdateRequest {
    private Long id;
    private String title;
}
