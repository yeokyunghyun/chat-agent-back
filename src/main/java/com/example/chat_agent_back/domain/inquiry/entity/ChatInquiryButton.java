package com.example.chat_agent_back.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "yl_cht_inq_btn")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatInquiryButton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inq_btn_id")
    private Long inqBtnId;

    @ManyToOne
    @JoinColumn(name = "inq_id")
    private ChatInquiry inquiry;

    @Column(length = 10)
    private String type;

    private String regrId;
    private LocalDateTime regrDttm;
    private String chngId;
    private LocalDateTime chngDttm;

}
