package com.example.chat_agent_back.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "yl_user_ready")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReady {

    @Id
    @Column(name = "user_id", length = 30)
    @Comment("사용자_ID")
    private String userId;

    @Column(name = "user_stat_cd", length = 15)
    @Comment("사용자_상태_코드")   // READY, NOT_READY
    private String userStatCd;

    @Column(name = "assign_cust_cnt")
    @Comment("고객_배분_개수")
    private Integer assignCustCnt;

}
