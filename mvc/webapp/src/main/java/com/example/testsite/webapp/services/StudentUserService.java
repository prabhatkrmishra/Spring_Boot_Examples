package com.example.testsite.webapp.services;

import com.example.testsite.webapp.model.User;
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
    public int signUp(String name, String gender, String location, String college) {
        if(studentUser.createUser(name, gender, location, college))
            return studentUser.saveUser();

        return -1;
    }
}
