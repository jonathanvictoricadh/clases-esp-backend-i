package com.dh.course.controller;

import com.dh.course.event.FinalizarCursoEventProducer;
import com.dh.course.model.Course;
import com.dh.course.service.CourseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    private final FinalizarCursoEventProducer finalizarCursoEventProducer;

    public CourseController(CourseService courseService, FinalizarCursoEventProducer finalizarCursoEventProducer) {
        this.courseService = courseService;
        this.finalizarCursoEventProducer = finalizarCursoEventProducer;
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



    @PatchMapping("/finalizarCurso")
    @ResponseStatus(code = HttpStatus.OK)
    public void finalizarCurso(){
        finalizarCursoEventProducer.publishFinalizarCursoEvent(new FinalizarCursoEventProducer.Data("Esp Back I", LocalDate.now(),10, "Felices Pascuas" ));
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
