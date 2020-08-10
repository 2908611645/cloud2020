package com.springcloud.alibaba.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    public static HashMap<Long, Payment> hashMap=new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"asdasdasdas"));
        hashMap.put(2L,new Payment(2L,"asdasdasdas"));
        hashMap.put(3L,new Payment(3L,"asdasdasdas"));
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymntSql(@PathVariable("id") Long id){
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result =new CommonResult<>(200,"from sql,serverport:"+serverport,payment);
        return result;
    }
}
