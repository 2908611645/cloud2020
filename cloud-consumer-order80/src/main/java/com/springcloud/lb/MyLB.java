package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements Loadbalancer {

     private AtomicInteger atomicInteger=new AtomicInteger(0);

     public  final int getAndINcrement(){
         int current;
         int next;
         do{
             current=this.atomicInteger.get();
             next = current >= 2147483647 ? 0 : current + 1;
         }while(!this.atomicInteger.compareAndSet(current,next));
         System.out.println("next:"+next);
         return next;
     }

     @Override
    public ServiceInstance in(List<ServiceInstance> serviceInstances) {
      int index=  getAndINcrement() % serviceInstances.size();
         return serviceInstances.get(index);
    }
}
