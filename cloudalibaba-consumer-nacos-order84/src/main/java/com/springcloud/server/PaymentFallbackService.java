package com.springcloud.server;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentServer{
    @Override
    public CommonResult<Payment> paymntSql(Long id) {
        return new CommonResult<>(400,"feign",new Payment(id,"eror"));
    }
}
