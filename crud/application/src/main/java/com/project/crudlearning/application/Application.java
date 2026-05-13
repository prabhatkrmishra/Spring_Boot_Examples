package com.project.crudlearning.application;

import com.project.crudlearning.application.models.Student;
import com.project.crudlearning.application.services.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        StudentService studentService = ctx.getBean(StudentService.class);
        Student student = new Student("lOLLA lASSAN", 18, "trans", 8);
        studentService.addStudent(student);
    }

}
