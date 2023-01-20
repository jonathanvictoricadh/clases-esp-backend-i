package com.dh.course.controller;

import com.dh.course.model.Course;
import com.dh.course.service.CourseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody Course course) {
        courseService.save(course);
        return ResponseEntity.ok(course.getCourseId());
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity update(@RequestBody Course course) {
        courseService.update(course);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/addStudent")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity addStudent(@RequestBody AddStudentDto addStudentDto) {
        try {
            courseService.addStudent(addStudentDto.getCourseId(), addStudentDto.getStudentId());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Getter
    @Setter
    static class AddStudentDto {
        private Long courseId;
        private Long studentId;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
