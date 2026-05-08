package com.example.testsite.webapp.domain;

import com.example.testsite.webapp.repository.StudentUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentUser implements User{

    @Autowired
    StudentUserDAO studentUserDAO;

    String name;
    String gender;
    String location;
    String college;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public boolean createUser(String name, String gender, String location, String college) {
        this.name = name;
        this.gender = gender;
        this.location = location;
        this.college = college;

        System.out.println(name + " | " +  gender + " | " + location + " | " + college);

        return true;
    }

    @Override
    public Integer saveUser() {
        return studentUserDAO.saveUser(this);
    }
}
