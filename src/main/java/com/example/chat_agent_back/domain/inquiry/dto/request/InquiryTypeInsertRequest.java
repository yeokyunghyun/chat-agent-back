package com.example.chat_agent_back.domain.inquiry.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryTypeInsertRequest {
    private Long parentId;
    private String username;
    private String title;
}
