package com.example.chat_agent_back.common.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class TalkStompChannelInterceptor implements ChannelInterceptor {

    // preSend :: 소켓 클라이언트 -> 서버 /app/agent/send
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return ChannelInterceptor.super.preSend(message, channel);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        ChannelInterceptor.super.postSend(message, channel, sent);
    }

    // preReceive :: 소켓 서버 -> 클라이언트 messageTemplate.convertAndSend
    @Override
    public boolean preReceive(MessageChannel channel) {
        System.out.println("??");
        return ChannelInterceptor.super.preReceive(channel);
    }


}
