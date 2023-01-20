package com.dh.course.service;

import com.dh.course.model.Course;
import com.dh.course.model.CourseStudent;
import com.dh.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;



    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void save(Course course) {
        courseRepository.save(course);
    }


    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }


    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public void update(Course course) {
        if (courseRepository.existsById(course.getCourseId())) {
            courseRepository.save(course);
        }
    }

    public void addStudent(Long idCourse, Long idStudent) throws Exception {
        var course = courseRepository.findById(idCourse);
        if (course.isPresent()) {

            course.get().getStudents().add(new CourseStudent(null, course.get(),idStudent));
            courseRepository.save(course.get());
        }else{
            throw new Exception("Course not found");
        }
    }
}
