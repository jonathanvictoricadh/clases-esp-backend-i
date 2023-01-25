package com.dh.course.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-course")
public interface CourseFeign {

    @GetMapping("/api/v1/courses/{id}")
    CourseFeign.Course getById(@PathVariable Long id);

    @Getter
    @Setter
    class Course {
        private Long courseId;
        private String name;
    }
}
