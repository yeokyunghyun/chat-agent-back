package com.example.chat_agent_back.domain.inquiry;

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
    private Long inqId;

    /*
       orphanRemoval = true
       ex) inquiry.getButtons().remove(button); 이 때 db 데이터 지워짐
     */
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatInquiryButton> buttons = new ArrayList<>();
    private Long inqBtnId;

    private String regrId;
    private LocalDateTime regrDttm;

    private String chngId;
    private LocalDateTime chngDttm;

}
