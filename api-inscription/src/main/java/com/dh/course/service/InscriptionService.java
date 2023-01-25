package com.dh.course.service;

import com.dh.course.client.CourseFeign;
import com.dh.course.client.StudentFeign;
import com.dh.course.model.Inscription;
import com.dh.course.repository.InscriptionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class InscriptionService {

    private final CourseFeign courseFeign;
    private final StudentFeign studentFeign;
    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(CourseFeign courseFeign, StudentFeign studentFeign, InscriptionRepository inscriptionRepository) {
        this.courseFeign = courseFeign;
        this.studentFeign = studentFeign;
        this.inscriptionRepository = inscriptionRepository;
    }

    public void create(Long studentId, Long courseId, BigDecimal tuitionAmount, LocalDate startInscription, LocalDate endInscription) {
        StudentFeign.Student student = findStudent(studentId);
        CourseFeign.Course course = findCourse(courseId);
        Inscription inscription = new Inscription();
        inscription.setId(UUID.randomUUID().toString());
        inscription.setEndInscription(endInscription);
        inscription.setStartInscription(startInscription);
        inscription.setTuitionAmount(tuitionAmount);
        inscription.setCourse(new Inscription.Course(course.getCourseId(), course.getName()));
        inscription.setStudent(new Inscription.Student(student.getStudentId(), student.getName(), student.getLastName()));
        inscriptionRepository.save(inscription);
    }
    @Retry(name = "retryInscription")
    @CircuitBreaker(name = "clientInscription", fallbackMethod = "findCourseFallBack")
    private CourseFeign.Course findCourse(Long courseId) {
        var course = courseFeign.getById(courseId);
        return course;
    }

    public CourseFeign.Course findCourseFallBack(Long courseId, Throwable t) throws Exception {
        throw new Exception("Not found Course");
    }
    @Retry(name = "retryInscription")
    @CircuitBreaker(name = "clientInscription", fallbackMethod = "findStudentFallBack")
    private StudentFeign.Student findStudent(Long studentId) {
        var student = studentFeign.getById(studentId);
        return student;
    }

    public StudentFeign.Student findStudentFallBack(Long studentId, Throwable t) throws Exception {
        throw new Exception("Not found Student");
    }
}
