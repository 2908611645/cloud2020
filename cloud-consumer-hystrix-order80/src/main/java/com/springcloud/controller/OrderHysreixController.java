package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHysreixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global")
public class OrderHysreixController {
    @Resource
    private PaymentHysreixService paymentHysreixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id){
        String result =paymentHysreixService.paymentinfo_ok(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "Time_outHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String Time_out(@PathVariable("id") Integer id){
        int a=10/0;
        String result =paymentHysreixService.Time_out(id);
        return result;
    }

    public String Time_outHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"80不等了:"+id;
    }
    //全局fallbace方法
    public String payment_Global(){
        return "全局错误";
    }
}
