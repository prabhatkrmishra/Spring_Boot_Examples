package com.example.testsite.webapp.services;

import com.example.testsite.webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUserService implements UserService {

    @Autowired
    User studentUser;

    @Override
    public User getUser() {
        return studentUser;
    }

    @Override
    public boolean signUp(String name, String gender, String location, String college) {
        boolean saved = studentUser.createUser(name, gender, location, college);
        studentUser.saveUser();
        return saved;
    }
}
