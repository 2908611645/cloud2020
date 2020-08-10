package com.springcloud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springcloud.server.ImessageProvider;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private ImessageProvider imessageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return imessageProvider.send();
    }
}
