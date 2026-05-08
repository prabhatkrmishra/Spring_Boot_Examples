package com.example.testsite.webapp.services;

import com.example.testsite.webapp.model.User;

public interface UserService {
    public User getUser();
    public int signUp(String name, String gender, String location, String college);
}
