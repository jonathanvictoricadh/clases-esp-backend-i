package com.dh.student;

import com.dh.student.model.Student;
import com.dh.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiStudentApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadData(StudentRepository repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            repository.save(new Student(11L,"Alumno 1", "Apellido 1"));
            repository.save(new Student(22L, "Alumno 2", "Apellido 2"));
            repository.save(new Student(33L,"Alumno 3", "Apellido 3"));
            repository.save(new Student(44L,"Alumno 4", "Apellido 4"));
        };
    }

}
