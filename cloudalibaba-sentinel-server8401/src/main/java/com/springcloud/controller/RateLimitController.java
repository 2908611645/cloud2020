package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.myhandel.CustomerHandel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
     return new CommonResult(200,"按资源名称测试限流",new Payment(2020L,"serial001"));
  }
   public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"服务不可用");
   }




    @GetMapping("/rateLimit/BlockHandler")
    @SentinelResource(value = "BlockHandler"
                 ,blockHandlerClass = CustomerHandel.class
                 ,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"自定义",new Payment(2020L,"serial001"));
    }
}
