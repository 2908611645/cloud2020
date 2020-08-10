package com.springcloud.server;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentServer {
    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymntSql(@PathVariable("id") Long id);
}
