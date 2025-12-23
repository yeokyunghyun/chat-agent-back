package com.example.chat_agent_back.chat.route.service;

import com.example.chat_agent_back.chat.main.dto.ChatRequest;
import com.example.chat_agent_back.domain.chat.entity.ChatMaster;
import com.example.chat_agent_back.domain.chat.repository.ChatMasterRepository;
import com.example.chat_agent_back.domain.cust.entity.CustReady;
import com.example.chat_agent_back.domain.cust.repository.CustReadyRepositry;
import com.example.chat_agent_back.domain.user.entity.UserReady;
import com.example.chat_agent_back.domain.user.repository.UserReadyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatRouterServiceImpl implements ChatRouterService {

    private final CustReadyRepositry custReadyRepositry;
    private final UserReadyRepository userReadyRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final RestTemplate restTemplate;
    private final ChatMasterRepository chatMasterRepository;

    @Override
    @Transactional
    public void routeOnce() {
        // 상담 대기중 요청 1건 가져오기(오래된 순)
        CustReady custReady = custReadyRepositry.findFirstByTalkStatCdOrderByRegrDttmAsc("pending")
                .orElse(null);
        if(custReady == null) return;

        System.out.println("상담 대기중 요청 1건 = " + custReady.getCustName());

        // 채팅 대기중인 상담사
        String userId = userReadyRepository
                .findFirstByUserStatCdOrderByAssignCustCntAsc("READY")
                .map(UserReady::getUserId)
                .orElse(null);
        if(userId == null) return;

        System.out.println("채팅 대기중인 상담사 = " + userId);

        // 배정된 상담 건 업데이트
        int deleted = custReadyRepositry.deleteIfStatusMatch(
                custReady.getUserKey(),
                "pending"
        );
        // 중복 방지
        if (deleted == 0) {
            return;
        }

        // 상담 마스터 insert
        ChatMaster mst = new ChatMaster();
        mst.setCustName(custReady.getCustName());
        mst.setTalkStatCd("pending");
        mst.setCustId(custReady.getUserKey());
        mst.setAgentId(userId);
        mst.setRegrDttm(custReady.getRegrDttm() != null ? custReady.getRegrDttm() : LocalDateTime.now());
        mst.setChngDttm(LocalDateTime.now());

        ChatMaster saved = chatMasterRepository.save(mst);

        ChatRequest req =  new ChatRequest();
        req.setCustomerId(custReady.getUserKey());
        req.setCustomerName(custReady.getCustName());
        req.setRequestTime(custReady.getRegrDttm());
        req.setStatus(custReady.getTalkStatCd());

        messagingTemplate.convertAndSend("/topic/agent/requests/" + userId, req);

        // 클라이언트 서버
        restTemplate.postForEntity(
                "http://localhost:3000/api/consultation-assigned",
                Map.of("customerId", req.getCustomerId()),
                Void.class
        );

        System.out.println("배정 완료: cust=" + custReady.getUserKey()
                + ", agent=" + userId
                + ", talkContactId=" + saved.getTalkContactId());

    }
}
