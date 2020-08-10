package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     *正常访问
     * @param id
     * @return
     */
    public String paymentinfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymenyinfo_ok,id:"+id+"\t"+"sd";
    }
   //爆异常处理
     @HystrixCommand(fallbackMethod = "Time_outHandler",commandProperties = {
             @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
     })
    public String Time_out(Integer id){
        try {
            TimeUnit.MICROSECONDS.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
       //  int age=10/0;
        return "线程池："+Thread.currentThread().getName()+"paymenyinfo_ok,id:"+id+"\t"+"sd耗时：";
    }

    public String Time_outHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"Time_outHandler,id:"+id;
    }


    //-----服务熔断
    @HystrixCommand(fallbackMethod = "payment_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id<0){
            throw new RuntimeException("*********id 不能负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"流水号："+serialNumber;
    }

    public String payment_fallback(@PathVariable("id") Integer id){
        return "id不能为负数";
    }
}
