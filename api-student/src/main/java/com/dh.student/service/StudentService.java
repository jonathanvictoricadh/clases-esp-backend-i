package com.dh.student.service;


import com.dh.student.model.Student;
import com.dh.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }


    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void update(Student Student) {
        if (studentRepository.existsById(Student.getStudentId())) {
            studentRepository.save(Student);
        }
    }
}
