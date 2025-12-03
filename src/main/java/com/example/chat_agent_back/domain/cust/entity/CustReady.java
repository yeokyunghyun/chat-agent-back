package com.example.chat_agent_back.domain.cust.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "yl_cust_ready")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustReady {

    @Id
    @Column(name = "user_key", length = 50)
    private String userKey;

    @Column(name = "talk_stat_cd", length = 10)
    private String talkStatCd;

    @Column(name = "regr_dttm")
    private LocalDateTime regrDttm;
}
