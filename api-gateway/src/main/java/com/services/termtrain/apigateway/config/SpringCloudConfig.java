package com.services.termtrain.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        System.out.println("__________________________________ gatewayRoutes -------------------------------------------------");

        return builder.routes()
//                  .route("second-service", r -> r.path("/consumer/**").uri("http://localhost:8082/second-service"))
                .route("auth-service", r -> r.path("/api/v1/**").uri("http://localhost:8082/auth-service"))
                .build();
    }

}
