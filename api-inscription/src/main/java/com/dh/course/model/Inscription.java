package com.dh.course.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Inscription")
public class Inscription {

    @Id
    private String id;
    private Inscription.Course course;
    private Inscription.Student student;
    private BigDecimal tuitionAmount;
    private LocalDate startInscription;
    private LocalDate endInscription;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Course{
        private Long courseId;
        private String name;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student{
        private Long studentId;
        private String name;
        private String lastName;
    }
}
