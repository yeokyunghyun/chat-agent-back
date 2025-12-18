package com.example.chat_agent_back.domain.user.service;

import com.example.chat_agent_back.domain.user.entity.UserReady;
import com.example.chat_agent_back.domain.user.repository.UserReadyRepository;
import com.example.chat_agent_back.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserReadyServiceImpl implements UserReadyService {
    private final UserReadyRepository userReadyRepository;


    @Transactional
    @Override
    public void upsertReadyOnLogin(String userId) {
        UserReady entity = userReadyRepository.findById(userId)
                .map(existing -> {
                    existing.setUserStatCd("NOT_READY");
                    existing.setAssignCustCnt(0);
                    return existing;
                })
                .orElseGet(() -> UserReady.builder()
                        .userId(userId)
                        .userStatCd("NOT_READY")
                        .assignCustCnt(0)
                        .build());

        userReadyRepository.save(entity);
    }
}
