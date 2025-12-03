package com.example.chat_agent_back.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private String userId;

    @Column(name = "assign_cust_cnt")
    private Integer assignCustCnt;

}
