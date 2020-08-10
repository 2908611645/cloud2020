package com.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback =  PaymentFallbackService.class)
public interface PaymentHysreixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String Time_out(@PathVariable("id") Integer id);
}
