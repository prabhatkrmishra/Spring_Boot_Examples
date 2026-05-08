package com.example.testsite.webapp.services;

import com.example.testsite.webapp.model.StudentUser;
import com.example.testsite.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StudentUserService implements UserService {

    /*
      Changed fixed autowired User bean to dynamic prototype bean creation
      using ApplicationContext.getBean(StudentUser.class).

      Why:
          Earlier same StudentUser object was reused for every signup because
          service is singleton. So all saved users pointed to same object and
          details page always showed latest registered user.

      Now:
          Because we are not saving and fetching from database so, a fresh StudentUser object
          have to be created for every registration request.
    */

    @Autowired
    ApplicationContext context;
    /* ApplicationContext itself is managed by Spring */

    @Override
    public User getUser() {
        return context.getBean(StudentUser.class);
    }

    @Override
    public int signUp(String name, String gender, String location, String college) {
        StudentUser studentUser = context.getBean(StudentUser.class);
        if (studentUser.createUser(name, gender, location, college))
            return studentUser.saveUser();

        return -1;
    }
}
