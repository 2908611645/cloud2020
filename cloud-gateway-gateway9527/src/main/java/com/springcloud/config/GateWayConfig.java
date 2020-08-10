package com.springcloud.config;

import cn.hutool.extra.template.engine.beetl.BeetlUtil;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route",
                 r ->r.path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

//    public static void main(String[] args) {
//        ZonedDateTime zbj=ZonedDateTime.now();
//        System.out.println(zbj);
//    }
}
