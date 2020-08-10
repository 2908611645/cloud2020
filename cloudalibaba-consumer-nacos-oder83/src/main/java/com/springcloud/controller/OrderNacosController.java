package com.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {

    @Value("${service-url.service-user}")
    private String serverurl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    private String paymentinfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serverurl+"/payment/nacos/"+id,String.class);
    }
}
