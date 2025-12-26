package com.example.chat_agent_back.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "yl_cht_inq")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;

    private String title;

    private String regrId;
    private LocalDateTime regrDttm;

    private String chngId;
    private LocalDateTime chngDttm;

    @PrePersist
    public void prePersist() {
        this.regrDttm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.chngDttm = LocalDateTime.now();
    }
}
