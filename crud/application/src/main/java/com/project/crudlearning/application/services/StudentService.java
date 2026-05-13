package com.project.crudlearning.application.services;

import com.project.crudlearning.application.models.Student;
import com.project.crudlearning.application.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public void addStudent(Student student) {
        studentRepository.addStudentToDb(student);
    }
}
