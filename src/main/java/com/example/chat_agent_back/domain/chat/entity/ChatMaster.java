package com.example.chat_agent_back.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "yl_cht_mst")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_contact_id")
    private Long talkContactId;

    @Column(name = "cust_id", length = 50)
    @Comment("고객_ID")
    private String custId;

    @Column(name = "cust_name", length = 15)
    @Comment("고객_명")
    private String custName;

    @Column(name = "talk_stat_cd")
    private String talkStatCd;

    @Column(name = "inq_id", length = 50)
    private String inqId;

    @Column(name = "agent_id", length = 30)
    private String agentId;

    private LocalDateTime regrDttm;
    private LocalDateTime chngDttm;

    // mappedBy = "chatMaster" => ChatMasterDetail에 있는 chatMaster를 가리킴
    // cascade = CascadeType.ALL => 부모 엔티티 (yl_cht_mst 지울 때 yl_cht_mst_dtl 다 지워짐)
    @OneToMany(mappedBy = "chatMaster", cascade = CascadeType.ALL)
    private List<ChatMasterDetail> details = new ArrayList<>();
}
