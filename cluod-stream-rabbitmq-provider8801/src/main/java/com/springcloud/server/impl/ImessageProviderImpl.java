package com.springcloud.server.impl;

import cn.hutool.core.lang.UUID;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import com.springcloud.server.ImessageProvider;

import javax.annotation.Resource;

@EnableBinding(Source.class)     //定义消息的推送管道
public class ImessageProviderImpl implements ImessageProvider {

    @Resource
    private MessageChannel output;   //消息发送管道

    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return null;
    }
}
