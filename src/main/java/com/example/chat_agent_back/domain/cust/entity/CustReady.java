package com.example.chat_agent_back.domain.cust.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;

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
    @Comment("사용자_키")
    private String userKey;

    @Column(name = "cust_name", length = 15)
    @Comment("고객_명")
    private String custName;

    @Column(name = "talk_stat_cd", length = 20)
    @Comment("채팅_상태_코드")
    private String talkStatCd;

    @Column(name = "regr_dttm")
    @Comment("등록_일시")
    private LocalDateTime regrDttm;
}
