package com.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.server.PaymentServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    @Value("${service-url.service-user}")
    private String serverurl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentServer paymentServer;

    @GetMapping("/consumer/fallback/{id}")
   // @SentinelResource(value = "fallback",fallback = "handlerfallback")//fallback只负责异常业务
   // @SentinelResource(value = "blockHandler",blockHandler = "blockHandler")//blockHandler只负责控制台异常业务
    @SentinelResource(value = "blockHandler",fallback = "handlerfallback",blockHandler = "blockHandler"
    ,exceptionsToIgnore = {IllegalArgumentException.class})//blockHandler只负责控制台异常业务   //exceptionsToIgnore忽略IllegalArgumentException异常
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
       CommonResult<Payment>result= restTemplate.getForObject(serverurl+"/payment/"+id,CommonResult.class,id);
       if (id ==4){
           throw new IllegalArgumentException("非法参数异常");
       }else if (result.getData()==null){
           throw new NullPointerException("id没有 对应数据");
       }
       return result;
    }
     //fallback
    public CommonResult handlerfallback(@PathVariable Long id,Throwable e){
        Payment payment=new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常:"+e.getMessage(),payment);
    }
     //blockhandel
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException){
        Payment payment=new Payment(id,"null");
        return new CommonResult<>(445,"blockException-sentinle限流 blockException："+blockException.getMessage(),payment);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> payment(@PathVariable("id") Long id){
        return paymentServer.paymntSql(id);
    }
}
