package com.dh.course.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-student")
@LoadBalancerClient(value = "stores", configuration = com.dh.course.config.LoadBalanceConfiguration.class)
public interface StudentFeign {

    @GetMapping("/api/v1/students/{id}")
    Student getById(@PathVariable Long id);

    @Getter
    @Setter
    class Student {
        private Long studentId;
        private String name;
        private String lastName;
    }
}
