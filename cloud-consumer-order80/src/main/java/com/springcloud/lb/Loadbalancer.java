package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface Loadbalancer  {
    ServiceInstance in(List<ServiceInstance> serviceInstances);
}
