package com.springcloud.controller;

import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class paymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id){
       String result=paymentService.paymentinfo_ok(id);
       return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String Time_out(@PathVariable("id") Integer id){
        String result=paymentService.Time_out(id);
        return result;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
