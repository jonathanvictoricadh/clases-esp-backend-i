package com.dh.course.configuration;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class LoadBalanceConfiguration {

    @Bean
    ReactorLoadBalancer<ServiceInstance> configure(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(
//                name, ServiceInstanceListSupplier.class), name
//        );

//        return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(
//                name, ServiceInstanceListSupplier.class), name
//        );

        return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(
                name, ServiceInstanceListSupplier.class), name
        );
    }
}
