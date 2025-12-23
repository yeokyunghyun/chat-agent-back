package com.example.chat_agent_back.domain.cust.repository;

import com.example.chat_agent_back.domain.cust.entity.CustReady;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustReadyRepositry extends JpaRepository<CustReady, String> {
    Optional<CustReady> findFirstByTalkStatCdOrderByRegrDttmAsc(String talkStatCd);

    // ✅ pending인 것만 삭제 (중복 배정 방지용 선점)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        delete from CustReady c
         where c.userKey = :userKey
           and c.talkStatCd = :current
    """)
    int deleteIfStatusMatch(@Param("userKey") String userKey,
                            @Param("current") String current);

}
