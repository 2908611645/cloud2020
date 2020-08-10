package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {
    public  static  final String INNOKE_URL="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;


    @RequestMapping("/conusmer/payment/zk")
    public String paymentzk(){
        String result=restTemplate.getForObject(INNOKE_URL+"/payment/zk",String.class);
         return result;
    }
}
