package com.example.chat_agent_back.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "yl_cht_mst_dtl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMasterDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtl_id")
    private Long dtlId;

    @ManyToOne
    @JoinColumn(name = "mst_id")
    private ChatMaster chatMaster;

    @Column(name = "snd_rcv_cd", length = 3)
    private String sndRcvCd;

    private String type;

    @Column(name = "after_msg", length = 5000)
    private String afterMsg;

    @Column(length = 5000)
    private String content;

    @Column(name = "regr_dttm")
    private LocalDateTime regrDttm;
}
