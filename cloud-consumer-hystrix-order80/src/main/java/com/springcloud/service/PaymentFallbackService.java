package com.springcloud.service;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHysreixService{
    @Override
    public String paymentinfo_ok(Integer id) {
        return "ok报错";
    }

    @Override
    public String Time_out(Integer id) {
        return "Time_out";
    }
}
