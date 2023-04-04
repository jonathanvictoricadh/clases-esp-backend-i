package com.dh.course.service;

import com.dh.course.client.StudentFeign;
import com.dh.course.model.Course;
import com.dh.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private StudentFeign studentFeign;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void init() {
        var course= new Course(11L,"Esp. Backend", new ArrayList<>());
        when(studentFeign.getById(eq(1L))).thenReturn(new StudentFeign.Student(1L,"jonathan",""));
        when(courseRepository.findById(eq(11L))).thenReturn(Optional.of(course));
        when(courseRepository.save(eq(course))).thenReturn(course);
    }

    @Test
    void addStudent() {
        try {
            courseService.addStudent(11L,1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}