package com.dh.course;

import com.dh.course.model.Course;
import com.dh.course.model.CourseStudent;
import com.dh.course.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class ApiCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCourseApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CourseRepository repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            var course1 = new Course(1L, "Esp. Back 1", null);
            var course2 = new Course(2L, "Esp. Back 2", null);
            var course3 = new Course(3L, "Esp. Back 3", null);
            List<CourseStudent> lista1 = new ArrayList<>();
            lista1.add(new CourseStudent(null, course1, 11L));
            course1.setStudents(lista1);

            List<CourseStudent> lista2 = new ArrayList<>();
            lista2.add(new CourseStudent(null, course2, 22L));
            course2.setStudents(lista2);

            List<CourseStudent> lista3 = new ArrayList<>();
            lista3.add(new CourseStudent(null, course3, 33L));
            course3.setStudents(lista3);

            repository.save(course1);
            repository.save(course2);
            repository.save(course3);
        };
    }
}
