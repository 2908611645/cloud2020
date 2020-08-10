package com.springcloud.myhandel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;

public class CustomerHandel {
    public static CommonResult handlerException(BlockException exception){
       return new CommonResult(444,"自定义，exception------1",new Payment(2020L,"23"));
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"自定义，exception------2",new Payment(2020L,"23"));
    }
}
