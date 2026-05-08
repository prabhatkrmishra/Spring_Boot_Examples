package com.example.testsite.webapp.repository;

import com.example.testsite.webapp.domain.StudentUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Scope("singleton")
public class StudentUserDAO implements DataAccessObject<StudentUser> {

    private final List<StudentUser> studentUserList =  new ArrayList<>();

    public List<StudentUser> getStudentUsers() {
        return studentUserList;
    }

    @Override
    public Optional<StudentUser> getUser(Integer id) {
        if(studentUserList.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(studentUserList.get(id));
    }

    @Override
    public int saveUser(StudentUser student) {
        int id = studentUserList.size();
        student.setId(id);
        studentUserList.add(student);
        System.out.println("Student user saved with id: " + id);
        return id;
    }
}
